package com.micro.cursos.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.micro.common.controllers.CommonController;
import com.micro.common.entities.Alumno;
import com.micro.common.entities.Examen;
import com.micro.cursos.entities.Curso;
import com.micro.cursos.entities.CursoAlumno;
import com.micro.cursos.services.CursoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class CursoController extends CommonController<Curso, CursoService> {
 
    
    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping("")
    public ResponseEntity<?> listar() {
        List<Curso> cursos  = (List<Curso>) service.findAll();

        cursos = cursos.stream().map(c->{
            c.getCursoAlumnos().forEach(ca->{
                Alumno alumno =  new Alumno();
                alumno.setId(ca.getAlumnoId());
                c.addAlumno(alumno);
            });
            return c;

        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(cursos);
    }
   
    @DeleteMapping("/eliminar-alumno/{id}")
    public ResponseEntity<?> eliminarCursoAlumnoPorId(@PathVariable Long id) {
        service.eliminarCursoAlumnoById(id);
        return ResponseEntity.noContent().build();
    }
    

    @GetMapping("/pagina")
    @Override
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Curso> cursos = service.findAll(pageable).map(c ->{
            c.getCursoAlumnos().forEach(ca->{
                Alumno alumno =  new Alumno();
                alumno.setId(ca.getAlumnoId());
                c.addAlumno(alumno);
            });
            return c;
        });
        return ResponseEntity.ok().body(cursos);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Curso> o = service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Curso curso = o.get();

        if(!curso.getCursoAlumnos().isEmpty()){
            List<Long> ids = curso.getCursoAlumnos()
                .stream().map(ca-> ca.getAlumnoId())
                .collect(Collectors.toList());
            
            List<Alumno> alumnos =  (List<Alumno>) service.obtenerAlumnosPorCurso(ids);
            curso.setAlumnos(alumnos);

        }

        return ResponseEntity.ok().body(curso);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }       
        Optional<Curso>  o = this.service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }   
        
        Curso dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }


    
    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> addAlumno(@PathVariable Long id, @RequestBody List<Alumno> alumnos) {
        
        Optional<Curso>  o = this.service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }   
        
        Curso dbCurso = o.get();
        alumnos.forEach(a->{
           CursoAlumno cA =  new CursoAlumno();
           cA.setAlumnoId(a.getId());
           cA.setCurso(dbCurso);
           dbCurso.addCursoAlumno(cA);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-alumnos")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        
        Optional<Curso>  o = this.service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }   
        
        Curso dbCurso = o.get();
        CursoAlumno cA = new CursoAlumno();
        cA.setAlumnoId(alumno.getId());

        dbCurso.removeCursoAlumno(cA);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> getAlumno(@PathVariable Long id) {

        Curso curso =  service.findCursoByAlumnoId(id);

        if(curso != null) {
            List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestaAlumno(id);
            List<Examen> examenes = curso.getExamenes().stream()
                .map(examen->{
                    if(examenesIds.contains(examen.getId())) {
                        examen.setRespondido(true);
                    } 

                    return examen;
                }).collect(Collectors.toList());

            curso.setExamenes(examenes);
    
        }
        return ResponseEntity.ok().body(curso);
    }
    
    
    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> addExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes) {
        
        Optional<Curso>  o = this.service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }   
        
        Curso dbCurso = o.get();
        examenes.forEach(dbCurso::addExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    
    @PutMapping("/{id}/eliminar-examenes")
    public ResponseEntity<?> eliminarExamenes(@PathVariable Long id, @RequestBody Examen examen) {
        
        Optional<Curso>  o = this.service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }   
        
        Curso dbCurso = o.get();
        
        dbCurso.removeExamen(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }




    @GetMapping("balanceador-test")   
    public ResponseEntity<?> balanceadorTest() {
       Map<String, Object> response = new HashMap<>();
       response.put("balanceador", balanceadorTest);
       response.put("cursos", service.findAll());

       return ResponseEntity.ok().body(response);
    }



    

}
