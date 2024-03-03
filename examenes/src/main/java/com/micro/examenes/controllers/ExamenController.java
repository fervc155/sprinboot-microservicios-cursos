package com.micro.examenes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.micro.common.controllers.CommonController;
import com.micro.common.entities.Examen;
import com.micro.examenes.services.ExamenService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @GetMapping("/respondidos-por-preguntas")
    public ResponseEntity<?> obtenerEIds(@RequestParam Iterable<Long> pIds) {
        return ResponseEntity.ok().body(service.findExamenesIdsaByPreguntaIds(null));

    }


  


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Examen examen, BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }
    
        Optional<Examen> o = service.findById(id);

        if(o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Examen edb = o.get();
        edb.setNombre(examen.getNombre());


        edb.getPreguntas()
        .stream()
        .filter(pdb-> !examen.getPreguntas().contains(pdb))
        .forEach(edb::removePreguntas);


        return ResponseEntity.ok().body(service.save(edb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> getMethodName(@PathVariable String term) {
        return ResponseEntity.ok().body(service.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> asignaturas(){
        return ResponseEntity.ok().body(service.findAllAsignaturas());
    }
    
    
}
