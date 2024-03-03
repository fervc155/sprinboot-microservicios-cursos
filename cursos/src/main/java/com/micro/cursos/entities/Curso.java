package com.micro.cursos.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.common.entities.Alumno;
import com.micro.common.entities.Examen;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name ="cursos")
public class Curso {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    
    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Transient
    private List<Alumno> alumnos;

    @JsonIgnoreProperties(value={"curso"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="curso", cascade = CascadeType.ALL)
    private List<CursoAlumno> cursoAlumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    @PrePersist
    public void prePersist()
    {
        this.createAt= new Date();
        this.cursoAlumnos = new ArrayList<>();
    }


    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
    }

    public List<Examen> getExamenes() {
        return examenes;
    }


    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    
    public void removeAlumno(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }

    
    public void removeExamen(Examen examen) {
        this.examenes.remove(examen);
    }


    public List<CursoAlumno> getCursoAlumnos() {
        return cursoAlumnos;
    }


    public void setCursoAlumnos(List<CursoAlumno> cursoAlumnos) {
        this.cursoAlumnos = cursoAlumnos;
    }

  
    public void addCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.add(cursoAlumno);
    }

    public void removeCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.remove(cursoAlumno);
    }

        
}
