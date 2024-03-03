package com.micro.cursos.services;

import com.micro.cursos.entities.Curso;


import com.micro.common.entities.Alumno;
import com.micro.common.services.CommonService;

public interface CursoService extends CommonService<Curso> {
    public Curso findCursoByAlumnoId(Long id);
    public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno( Long id);
    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);
    public void eliminarCursoAlumnoById(Long id);

}
