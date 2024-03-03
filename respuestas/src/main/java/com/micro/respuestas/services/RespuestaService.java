package com.micro.respuestas.services;

import com.micro.common.entities.Respuesta;

public interface RespuestaService {

    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
    public Iterable<Respuesta> findRespuestaByAlumnoExamen(Long aId, Long eId);
    public Iterable<Long> findExamenesIdsaByAlumno(Long aid);
    public Iterable<Respuesta> findByAlumnoId(Long aId);

}
