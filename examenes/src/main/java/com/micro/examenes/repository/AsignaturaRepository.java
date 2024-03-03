package com.micro.examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.micro.common.entities.Asignatura;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long>{


    @Query("select e from Asignatura e where e.nombre like %?1%")
        public List<Asignatura> findByNombre(String term);
}
