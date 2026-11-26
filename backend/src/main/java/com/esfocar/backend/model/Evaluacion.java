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
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rubricaId;

    private String tituloRubrica;

    private Long estudianteId;

    private String nombreEstudiante;

    private Long profesorId;

    private Double notaFinal;

    private String observacion;

    private String fechaEvaluacion;

    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEvaluacion> detalles = new ArrayList<>();

    public Evaluacion() {
    }

    public Evaluacion(Long rubricaId, String tituloRubrica, Long estudianteId, String nombreEstudiante,
                      Long profesorId, Double notaFinal, String observacion, String fechaEvaluacion) {
        this.rubricaId = rubricaId;
        this.tituloRubrica = tituloRubrica;
        this.estudianteId = estudianteId;
        this.nombreEstudiante = nombreEstudiante;
        this.profesorId = profesorId;
        this.notaFinal = notaFinal;
        this.observacion = observacion;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRubricaId() {
        return rubricaId;
    }

    public void setRubricaId(Long rubricaId) {
        this.rubricaId = rubricaId;
    }

    public String getTituloRubrica() {
        return tituloRubrica;
    }

    public void setTituloRubrica(String tituloRubrica) {
        this.tituloRubrica = tituloRubrica;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public List<DetalleEvaluacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleEvaluacion> detalles) {
        this.detalles.clear();
        if (detalles != null) {
            for (DetalleEvaluacion detalle : detalles) {
                addDetalle(detalle);
            }
        }
    }

    public void addDetalle(DetalleEvaluacion detalle) {
        detalles.add(detalle);
        detalle.setEvaluacion(this);
    }
}
