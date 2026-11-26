package com.esfocar.backend.repository;

import com.esfocar.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoOrRut(String correo, String rut);

    List<Usuario> findByRol(String rol);
}
