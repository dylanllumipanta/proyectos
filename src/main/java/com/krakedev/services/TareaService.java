package com.krakedev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakedev.entidades.Empleado;
import com.krakedev.entidades.Proyecto;
import com.krakedev.entidades.Tarea;
import com.krakedev.repositories.EmpleadoRepository;
import com.krakedev.repositories.ProyectoRepository;
import com.krakedev.repositories.TareaRepository;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public TareaService(
            TareaRepository tareaRepository,
            ProyectoRepository proyectoRepository,
            EmpleadoRepository empleadoRepository) {
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Transactional
    public Tarea guardar(Tarea tarea) {
        if (tarea.getProyecto() == null) {
            throw new IllegalArgumentException("Debe indicar un proyecto");
        }

        int proyectoId = tarea.getProyecto().getId();

        Proyecto proyecto = proyectoRepository.findById(proyectoId)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        List<Empleado> empleados = new ArrayList<>();

        if (tarea.getEmpleados() != null) {
            for (Empleado empleado : tarea.getEmpleados()) {
                Empleado existente = empleadoRepository
                    .findById(empleado.getId())
                    .orElseThrow(() ->
                        new RuntimeException("Empleado no encontrado: "
                            + empleado.getId()));

                empleados.add(existente);
            }
        }

        tarea.setProyecto(proyecto);
        tarea.setEmpleados(empleados);

        return tareaRepository.save(tarea);
    }

    @Transactional(readOnly = true)
    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }
}