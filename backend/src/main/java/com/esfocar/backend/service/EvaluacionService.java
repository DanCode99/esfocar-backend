package com.esfocar.backend.service;

import com.esfocar.backend.model.DetalleEvaluacion;
import com.esfocar.backend.model.Evaluacion;
import com.esfocar.backend.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    public List<Evaluacion> listarPorEstudiante(Long estudianteId) {
        return evaluacionRepository.findByEstudianteId(estudianteId);
    }

    @Transactional
    public Evaluacion guardar(Evaluacion evaluacion) {
        prepararEvaluacion(evaluacion, false);
        return evaluacionRepository.save(evaluacion);
    }

    @Transactional
    public List<Evaluacion> sincronizar(List<Evaluacion> evaluaciones) {
        for (Evaluacion evaluacion : evaluaciones) {
            prepararEvaluacion(evaluacion, true);
        }
        return evaluacionRepository.saveAll(evaluaciones);
    }

    private void prepararEvaluacion(Evaluacion evaluacion, boolean limpiarIdTemporal) {
        if (limpiarIdTemporal && evaluacion.getId() != null && evaluacion.getId() <= 0) {
            evaluacion.setId(null);
        }

        List<DetalleEvaluacion> detalles = evaluacion.getDetalles();
        if (detalles != null) {
            for (DetalleEvaluacion detalle : detalles) {
                if (limpiarIdTemporal && detalle.getId() != null && detalle.getId() <= 0) {
                    detalle.setId(null);
                }
                detalle.setEvaluacion(evaluacion);
            }
        }

        double notaCalculada = evaluacion.getNotaFinal() != null
                ? evaluacion.getNotaFinal()
                : calcularNotaDesdeDetalles(detalles);
        evaluacion.setNotaFinal(truncarYLimitarNota(notaCalculada));
    }

    private double calcularNotaDesdeDetalles(List<DetalleEvaluacion> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return 1.00;
        }
        double logroTotal = 0.0;
        for (DetalleEvaluacion detalle : detalles) {
            double max = detalle.getPuntajeMaximo() != null && detalle.getPuntajeMaximo() > 0
                    ? detalle.getPuntajeMaximo()
                    : 0.0;
            double obtenido = detalle.getPuntajeObtenido() != null ? detalle.getPuntajeObtenido() : 0.0;
            double porcentaje = detalle.getPorcentaje() != null ? detalle.getPorcentaje() : 0.0;
            if (max > 0) {
                logroTotal += (obtenido / max) * (porcentaje / 100.0);
            }
        }
        return 1.0 + (logroTotal * 6.0);
    }

    private double truncarYLimitarNota(double nota) {
        double limitada = Math.max(1.00, Math.min(7.00, nota));
        return Math.floor(limitada * 100.0) / 100.0;
    }
}
