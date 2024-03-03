package com.micro.common.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.micro.common.services.CommonService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public class CommonController<E, S extends CommonService<E>> {
    
    @Autowired
    protected S service;

    @GetMapping("")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }
    
    
     @GetMapping("/pagina")
     public ResponseEntity<?> listar(Pageable pageable) {
         return ResponseEntity.ok().body(service.findAll(pageable));
     }
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {

        Optional<E> a = service.findById(id);

        if(a.isPresent()) {
            return ResponseEntity.ok().body(a.get());
        }

        return ResponseEntity.notFound().build();
    }
    

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }
        
        return ResponseEntity.created(null).body(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo: "+err.getField()+" "+err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
}