package com.esfocar.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_evaluacion")
public class DetalleEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long criterioId;

    private String nombreCriterio;

    private Double puntajeObtenido;

    private Double puntajeMaximo;

    private Integer porcentaje;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    @JsonIgnore
    private Evaluacion evaluacion;

    public DetalleEvaluacion() {
    }

    public DetalleEvaluacion(Long criterioId, String nombreCriterio, Double puntajeObtenido,
                             Double puntajeMaximo, Integer porcentaje, Evaluacion evaluacion) {
        this.criterioId = criterioId;
        this.nombreCriterio = nombreCriterio;
        this.puntajeObtenido = puntajeObtenido;
        this.puntajeMaximo = puntajeMaximo;
        this.porcentaje = porcentaje;
        this.evaluacion = evaluacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCriterioId() {
        return criterioId;
    }

    public void setCriterioId(Long criterioId) {
        this.criterioId = criterioId;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public void setNombreCriterio(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
    }

    public Double getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(Double puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public Double getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(Double puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}
