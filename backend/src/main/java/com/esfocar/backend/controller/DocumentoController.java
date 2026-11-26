package com.esfocar.backend.controller;

import com.esfocar.backend.model.Documento;
import com.esfocar.backend.service.DocumentoService;
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
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "*")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<Documento>> listarTodos() {
        return ResponseEntity.ok(documentoService.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Documento>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(documentoService.listarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Documento> guardar(@RequestBody Documento documento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.guardar(documento));
    }

    @PostMapping("/sync")
    public ResponseEntity<List<Documento>> sincronizar(@RequestBody List<Documento> documentos) {
        return ResponseEntity.ok(documentoService.sincronizar(documentos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = documentoService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
