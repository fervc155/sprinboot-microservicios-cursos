package com.micros.usuarios.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.micro.common.entities.Alumno;


public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long>, CrudRepository<Alumno, Long>{

    @Query("select a from Alumno a where upper(a.nombre) like %?1% or upper(a.apellido) like %?1%")
    public List<Alumno> findByNombreOrApellido(String term);


    public Iterable<Alumno> findAllByOrderByIdAsc();
    public Page<Alumno> findAllByOrderByIdAsc(Pageable pageable);
    
}
