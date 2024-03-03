package com.micro.examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.micro.common.entities.Examen;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long>, CrudRepository<Examen, Long>{


        @Query("select e from Examen e where e.nombre like %?1%")
        public List<Examen> findByNombre(String term);

        @Query("select e.id from Pregunta p  join.examen e where p.id in ?1 group by e.id")
        public Iterable<Long> findExamenesIdsaByPreguntaIds(Iterable<Long> ids);

}
