package com.micro.respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.micro.common.entities.Examen;
import java.util.List;

@FeignClient(name = "microservicio-examenes")
public interface ExamenClient {

    @GetMapping("/{id}")
    public Examen obtenerExamenById(@PathVariable Long id);

    @GetMapping("/respondidos-por-preguntas")
    public List<Long> obtenerEIds(@RequestParam List<Long> pIds);
   
}
