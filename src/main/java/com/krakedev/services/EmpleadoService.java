package com.krakedev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.entidades.Empleado;
import com.krakedev.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    public List<Empleado> listar() {
        return repository.findAll();
    }

    public Optional<Empleado> buscarPorId(int id) {
        return repository.findById(id);
    }

    public Empleado actualizar(int id, Empleado datos) {
        Empleado empleado = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setNombre(datos.getNombre());
        empleado.setCargo(datos.getCargo());

        return repository.save(empleado);
    }

    public void eliminar(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }

        repository.deleteById(id);
    }
}