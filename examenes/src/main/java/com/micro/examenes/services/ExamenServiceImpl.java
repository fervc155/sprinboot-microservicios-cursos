package com.micro.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.micro.common.entities.Asignatura;
import com.micro.common.entities.Examen;
import com.micro.common.services.impl.CommonServiceImpl;
import com.micro.examenes.repository.AsignaturaRepository;
import com.micro.examenes.repository.ExamenRepository;

import jakarta.transaction.Transactional;

public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {
        
        @Autowired
        private AsignaturaRepository aRepository;

        @Override
        @Transactional
        public List<Examen> findByNombre(String term){
            return repository.findByNombre(term);
        }

        @Override
        @Transactional
        public List<Asignatura> findAllAsignaturas() {
            return (List<Asignatura>) aRepository.findAll();
        }

        @Override
        @Transactional
        public Iterable<Long> findExamenesIdsaByPreguntaIds(Iterable<Long> ids) {
            return repository.findExamenesIdsaByPreguntaIds(ids);
        }

}
