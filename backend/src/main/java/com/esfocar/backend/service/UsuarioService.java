package com.esfocar.backend.service;

import com.esfocar.backend.model.Usuario;
import com.esfocar.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String identificador, String password) {
        if (identificador == null || password == null) {
            return Optional.empty();
        }
        return usuarioRepository.findByCorreoOrRut(identificador, identificador)
                .filter(usuario -> password.equals(usuario.getPassword()));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarEstudiantes() {
        return usuarioRepository.findByRol("ESTUDIANTE");
    }

    public Usuario registrar(Usuario usuario) {
        if (usuario.getPrimerIngreso() == null) {
            usuario.setPrimerIngreso(true);
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> actualizarPassword(Long id, String nuevaPassword) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setPassword(nuevaPassword);
            usuario.setPrimerIngreso(false);
            return usuarioRepository.save(usuario);
        });
    }

    public boolean eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}
