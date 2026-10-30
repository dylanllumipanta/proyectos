package com.krakedev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}