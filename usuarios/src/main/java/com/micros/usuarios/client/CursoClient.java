package com.micros.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;



@FeignClient(name="microservicio-cursos")
public interface CursoClient {
    
    @DeleteMapping("/eliminar-alumno/{id}")
    public void eliminarCursoAlumnoPorId(Long id);

}
