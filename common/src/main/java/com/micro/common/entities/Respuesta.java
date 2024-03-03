package com.micro.common.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "respuestas")
public class Respuesta {

    @Id
    private String id;

    private String texto;

    private Alumno alumno;


    private Long alumnoId;


    private Pregunta pregunta;

    private Long preguntaId;

    


    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

 
    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    

}
