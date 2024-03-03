package com.micro.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.common.entities.Alumno;
import com.micro.common.services.impl.CommonServiceImpl;
import com.micro.cursos.clients.AlumnoClient;
import com.micro.cursos.clients.RespuestaClient;
import com.micro.cursos.entities.Curso;
import com.micro.cursos.repositories.CursoRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoServiceImpl  extends CommonServiceImpl<Curso, CursoRepository> implements CursoService{
    
    @Autowired
    private RespuestaClient rClient;
   
    @Autowired
    private AlumnoClient aClient;

    @Override
    @Transactional
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(Long id) {
        return rClient.obtenerExamenesIdsConRespuestaAlumno(id);  
    }

    @Override
    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
        return aClient.obtenerAlumnosPorCurso(ids);
    }

    @Transactional
    @Override
    public void eliminarCursoAlumnoById(Long id) {
        repository.eliminarCursoAlumnoById(id);
    }

}
