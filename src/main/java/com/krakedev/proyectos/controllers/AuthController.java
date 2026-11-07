package com.krakedev.proyectos.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {

        try {
            Usuario registrado = usuarioService.registrar(usuario);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Usuario registrado correctamente");
            respuesta.put("username", registrado.getUsername());
            respuesta.put("rol", registrado.getRol());

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());

            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario datos) {

        try {
            Usuario usuario = usuarioService.login(
                    datos.getUsername(),
                    datos.getPassword()
            );

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Login correcto");
            respuesta.put("username", usuario.getUsername());
            respuesta.put("rol", usuario.getRol());

            return ResponseEntity.ok(respuesta);

        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
