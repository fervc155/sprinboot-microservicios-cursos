package com.micro.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.common.entities.Respuesta;
import com.micro.respuestas.clients.ExamenClient;
import com.micro.respuestas.repositories.RespuestaRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenClient eClient;


    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
       
        return repository.saveAll(respuestas);
    }
    
    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoExamen(Long aId, Long eId) {
       // Examen e = eClient.obtenerExamenById(eId);
        // List<Pregunta> preguntas = e.getPreguntas();
        // List<Long> preguntasIds = preguntas.stream().map(p -> p.getId())
        // .collect(Collectors.toList());

        // List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByPreguntaIds(eId, preguntasIds);

        // respuestas = respuestas.stream().map(r -> {
        //     preguntas.forEach(p -> {
        //         if(p.getId() == r.getPreguntaId()) {
        //             r.setPregunta(p);
        //         }
        //     });

        //     return r;
        // }).collect(Collectors.toList());
    
        return  repository.findExamenesIdsaByAlumno(aId, eId);
    
    }

    @Override
    public Iterable<Long> findExamenesIdsaByAlumno(Long aid) {
        // List<Respuesta> rAlumno = (List<Respuesta>) repository.findByAlumnoId(aid);
        
        // List<Long> eIds = Collections.emptyList();
        // if(rAlumno.size()>0) {
        //     List<Long> pregintaIds = rAlumno.stream().map(r-> r.getPreguntaId())
        //     .collect(Collectors.toList());
        //     eIds = eClient.obtenerEIds(pregintaIds);

        // }


        

        return ((List<Respuesta>)repository.findExamenesIdsConRespuestasByAlumno(aid))
        .stream().map(r->r.getPregunta().getExamen().getId())
        .distinct()
        .collect(Collectors.toList());

    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long aId) {
        return repository.findByAlumnoId(aId);
    }

}
