package com.micro.respuestas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.micro.common.entities.Respuesta;

@Repository
public interface RespuestaRepository  extends MongoRepository<Respuesta, String> {
 

    // @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id?2")
    // public Iterable<Respuesta> findRespuestaByAlumnoExamen(Long aId, Long eId);

  
    // @Query("select e.id from Respuesta r join r.pregunta p join.examen e where r.alumnoId=?1 group by e.id")
    // public Iterable<Long> findExamenesIdsaByAlumno(Long aid);


    @Query("{'alumnoId': ?0, 'preguntaId': { $in: ?1} }")
    public Iterable<Respuesta> findRespuestaByAlumnoByPreguntaIds(Long alumnoId, Iterable<Long> preguntaIds);


    @Query("{'alumnoId':?0}")
    public Iterable<Respuesta> findByAlumnoId(Long aId);

    @Query("{'alumnoId':?0, 'pregunta.examen.id': ?1}")
    public Iterable<Respuesta> findExamenesIdsaByAlumno(Long aid, Long eId);
    
    @Query(value = "{'alumnoId' : ?0 }", fields = "{pregunta.examen.id: 1}")
    public Iterable<Respuesta> findExamenesIdsConRespuestasByAlumno(Long aId );



}
