package com.micros.usuarios.services;

import com.micro.common.entities.Alumno;
import com.micro.common.services.CommonService;

import java.util.List;


public interface AlumnoService extends CommonService<Alumno>{

    public List<Alumno> findByNombreOrApellido(String term);

    Iterable<Alumno> findAllById(Iterable <Long> ids);

    public void eliminarCursoAlumnoPorId(Long id);

}



