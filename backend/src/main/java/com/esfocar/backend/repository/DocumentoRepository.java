package com.esfocar.backend.repository;

import com.esfocar.backend.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByUsuarioId(Long usuarioId);
}
