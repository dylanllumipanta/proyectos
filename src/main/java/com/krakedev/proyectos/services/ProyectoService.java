package com.krakedev.proyectos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.ProyectoRepository;

@Service
public class ProyectoService {

    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public Proyecto guardar(Proyecto proyecto) {
        return repository.save(proyecto);
    }

    public List<Proyecto> listar() {
        return repository.findAll();
    }

    public Optional<Proyecto> buscarPorId(int id) {
        return repository.findById(id);
    }

    public Proyecto actualizar(int id, Proyecto datos) {
        Proyecto proyecto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyecto.setNombre(datos.getNombre());
        proyecto.setDescripcion(datos.getDescripcion());
        proyecto.setFechaInicio(datos.getFechaInicio());

        return repository.save(proyecto);
    }

    public void eliminar(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        repository.deleteById(id);
    }
}