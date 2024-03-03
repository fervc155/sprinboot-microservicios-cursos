package com.micro.common.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.micro.common.services.CommonService;

import jakarta.transaction.Transactional;

@Service
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long> & CrudRepository<E, Long>   > implements CommonService<E>{

    @Autowired
    protected R repository;


    @Override
    @Transactional
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<E> findById(Long id) {
       return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
       return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        deleteById(id);
    }
    
}
