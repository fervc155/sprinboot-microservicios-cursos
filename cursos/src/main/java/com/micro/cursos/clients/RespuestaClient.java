package com.micro.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-respuestas")
public interface RespuestaClient {

    @GetMapping("/alumno/{id}/examenes-respondidos")
    public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(@PathVariable Long id);
    
    
}
