package com.micros.usuarios.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.micro.common.entities.Alumno;
import com.micro.common.services.impl.CommonServiceImpl;
import com.micros.usuarios.client.CursoClient;
import com.micros.usuarios.repositories.AlumnoRepository;
import com.micros.usuarios.services.AlumnoService;

import jakarta.transaction.Transactional;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

    @Autowired
    private CursoClient client;

    @Override
    @Transactional
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term.toUpperCase());
    }

    @Override
    @Transactional
    public Iterable<Alumno> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) {
        client.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional
    public Iterable<Alumno> findAll() {
        return repository.findAllByOrderByIdAsc();   
    }

    @Override
    @Transactional
    public Page<Alumno> findAll(Pageable pageable) {
        return repository.findAllByOrderByIdAsc(pageable);
    }

    
    
    


}
