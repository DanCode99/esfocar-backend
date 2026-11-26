package com.esfocar.backend.controller;

import com.esfocar.backend.model.Evaluacion;
import com.esfocar.backend.service.EvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarTodas() {
        return ResponseEntity.ok(evaluacionService.listarTodas());
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Evaluacion>> listarPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(evaluacionService.listarPorEstudiante(estudianteId));
    }

    @PostMapping
    public ResponseEntity<Evaluacion> guardar(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionService.guardar(evaluacion));
    }

    @PostMapping("/sync")
    public ResponseEntity<List<Evaluacion>> sincronizar(@RequestBody List<Evaluacion> evaluaciones) {
        return ResponseEntity.ok(evaluacionService.sincronizar(evaluaciones));
    }
}
