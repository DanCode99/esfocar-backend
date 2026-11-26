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
@Table(name = "criterios_rubrica")
public class CriterioRubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer porcentaje;

    private Double puntajeMax;

    @ManyToOne
    @JoinColumn(name = "rubrica_id")
    @JsonIgnore
    private Rubrica rubrica;

    public CriterioRubrica() {
    }

    public CriterioRubrica(String nombre, Integer porcentaje, Double puntajeMax, Rubrica rubrica) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.puntajeMax = puntajeMax;
        this.rubrica = rubrica;
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

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Double getPuntajeMax() {
        return puntajeMax;
    }

    public void setPuntajeMax(Double puntajeMax) {
        this.puntajeMax = puntajeMax;
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }
}
