package com.esfocar.backend.controller;

import com.esfocar.backend.model.Rubrica;
import com.esfocar.backend.service.RubricaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rubricas")
@CrossOrigin(origins = "*")
public class RubricaController {

    private final RubricaService rubricaService;

    public RubricaController(RubricaService rubricaService) {
        this.rubricaService = rubricaService;
    }

    @GetMapping
    public ResponseEntity<List<Rubrica>> listarTodas() {
        return ResponseEntity.ok(rubricaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Rubrica rubrica) {
        try {
            Rubrica guardada = rubricaService.guardar(rubrica);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/sync")
    public ResponseEntity<?> sincronizar(@RequestBody List<Rubrica> rubricas) {
        try {
            List<Rubrica> persistidas = rubricaService.sincronizar(rubricas);
            return ResponseEntity.ok(persistidas);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminada = rubricaService.eliminar(id);
        if (eliminada) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
