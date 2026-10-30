package com.krakedev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.entidades.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}