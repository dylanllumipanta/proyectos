package com.krakedev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.entidades.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}