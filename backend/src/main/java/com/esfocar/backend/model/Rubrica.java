package com.esfocar.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rubricas")
public class Rubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String asignatura;

    private Double escalaMaxima = 7.00;

    private Long profesorId;

    @OneToMany(mappedBy = "rubrica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriterioRubrica> criterios = new ArrayList<>();

    public Rubrica() {
    }

    public Rubrica(String titulo, String asignatura, Double escalaMaxima, Long profesorId) {
        this.titulo = titulo;
        this.asignatura = asignatura;
        this.escalaMaxima = escalaMaxima != null ? escalaMaxima : 7.00;
        this.profesorId = profesorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Double getEscalaMaxima() {
        return escalaMaxima;
    }

    public void setEscalaMaxima(Double escalaMaxima) {
        this.escalaMaxima = escalaMaxima;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public List<CriterioRubrica> getCriterios() {
        return criterios;
    }

    public void setCriterios(List<CriterioRubrica> criterios) {
        this.criterios.clear();
        if (criterios != null) {
            for (CriterioRubrica criterio : criterios) {
                addCriterio(criterio);
            }
        }
    }

    public void addCriterio(CriterioRubrica criterio) {
        criterios.add(criterio);
        criterio.setRubrica(this);
    }
}
