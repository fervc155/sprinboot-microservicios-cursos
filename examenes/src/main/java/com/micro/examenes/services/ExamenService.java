package com.micro.examenes.services;

import java.util.List;

import com.micro.common.entities.Asignatura;
import com.micro.common.entities.Examen;
import com.micro.common.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
    public List<Examen> findByNombre(String term);
    public List<Asignatura> findAllAsignaturas();
    public Iterable<Long> findExamenesIdsaByPreguntaIds(Iterable<Long> ids);


}
