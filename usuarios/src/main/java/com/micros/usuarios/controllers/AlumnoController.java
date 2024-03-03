package com.micros.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.micro.common.controllers.CommonController;
import com.micro.common.entities.Alumno;
import com.micros.usuarios.services.AlumnoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import java.util.List;



@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>  {

    @GetMapping("/alumnos-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok().body(service.findAllById(ids));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Alumno alumno, BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }
            
        Optional<Alumno> o = service.findById(id);

        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Alumno alumnodb= o.get();
        alumnodb.setNombre(alumno.getNombre());
        alumnodb.setApellido(alumno.getApellido());
        alumnodb.setEmail(alumno.getEmail());

        return ResponseEntity.created(null).body(service.save(alumnodb));

    }


    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok().body(service.findByNombreOrApellido(term));
    }


    @PostMapping
    public ResponseEntity<?> savePhoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        
        if(archivo.isEmpty()) {
            alumno.setFoto(archivo.getBytes());
        }
        return super.save(alumno, result);
    }


    @PutMapping("/{id}/foto")
    public ResponseEntity<?> updateFoto(@PathVariable Long id, @Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if(result.hasErrors()){
            return validar(result);
        }
            
        Optional<Alumno> o = service.findById(id);

        if(!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Alumno alumnodb= o.get();
        alumnodb.setNombre(alumno.getNombre());
        alumnodb.setApellido(alumno.getApellido());
        alumnodb.setEmail(alumno.getEmail());
        if(archivo.isEmpty()) {
            alumnodb.setFoto(archivo.getBytes());
        }
        return ResponseEntity.created(null).body(service.save(alumnodb));

    }

    @GetMapping("uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Alumno> o = service.findById(id);
        if(o.isEmpty() || o.get().getFoto()==null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(o.get().getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);


    }
    


}