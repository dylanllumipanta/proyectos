package com.krakedev.proyectos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(Usuario usuario) {

        Optional<Usuario> existente =
                usuarioRepository.findByUsername(usuario.getUsername());

        if (existente.isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        String passwordEncriptada =
                BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(10));

        usuario.setPassword(passwordEncriptada);

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String username, String password) {

        Optional<Usuario> resultado =
                usuarioRepository.findByUsername(username);

        if (resultado.isEmpty()) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        Usuario usuario = resultado.get();

        boolean valido = BCrypt.checkpw(
                password,
                usuario.getPassword()
        );

        if (!valido) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        return usuario;
    }
}
