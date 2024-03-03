package com.micro.respuestas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.micro.common.entities.Respuesta;
import com.micro.respuestas.services.RespuestaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService service;  

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Iterable<Respuesta> respuestas) {
        respuestas = ((List<Respuesta>) respuestas).stream()
        .map(r->{
            r.setAlumnoId(r.getAlumno().getId());
            r.setPreguntaId(r.getPregunta().getId());
            return r;
        }).collect(Collectors.toList());

        Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
        return ResponseEntity.ok().body(respuestasDb);
        
    }

    @GetMapping("/alumno/{aid}/examen/{eid}")
    public ResponseEntity<?> obtenerAlumnoExamen(@PathVariable Long aid, @PathVariable Long eid) {
        Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoExamen(aid, eid);

        return ResponseEntity.ok().body(respuestas);
    }

    @GetMapping("/alumno/{id}/examenes-respondidos")
    public ResponseEntity<?> obtenerExamenesIds(@PathVariable Long id) {
        Iterable<Long> examenesIds = service.findExamenesIdsaByAlumno(id);
        return ResponseEntity.ok().body(examenesIds);
    }
    
    
    

}  
