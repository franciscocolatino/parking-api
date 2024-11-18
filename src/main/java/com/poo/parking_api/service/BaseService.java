package com.poo.parking_api.service;

import com.poo.parking_api.domain.parking.Parking;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    // Método para criar uma nova entidade
    T create(T entity);

    // Método para atualizar uma entidade existente
    T update(T entity);

    // Método para buscar uma entidade por seu ID
    T findById(ID id);

    // Método para listar todas as entidades
    List<T> findAll();

    // Método para deletar uma entidade
    void delete(ID id);
}

