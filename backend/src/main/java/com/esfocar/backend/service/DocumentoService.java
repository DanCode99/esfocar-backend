package com.esfocar.backend.service;

import com.esfocar.backend.model.Documento;
import com.esfocar.backend.repository.DocumentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listarTodos() {
        return documentoRepository.findAll();
    }

    public List<Documento> listarPorUsuario(Long usuarioId) {
        return documentoRepository.findByUsuarioId(usuarioId);
    }

    public Documento guardar(Documento documento) {
        return documentoRepository.save(documento);
    }

    @Transactional
    public List<Documento> sincronizar(List<Documento> documentos) {
        for (Documento documento : documentos) {
            if (documento.getId() != null && documento.getId() <= 0) {
                documento.setId(null);
            }
        }
        return documentoRepository.saveAll(documentos);
    }

    public boolean eliminar(Long id) {
        if (!documentoRepository.existsById(id)) {
            return false;
        }
        documentoRepository.deleteById(id);
        return true;
    }
}
