package com.proyecto.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.demo.model.Categorias;

public interface CategoriasRepository extends CrudRepository<Categorias, Integer> {

    //save
    Categorias save(Categorias categorias);

    //findAll
    Iterable<Categorias> findAll();

    //findById
    Categorias findById(int id);

    //delete
    void deleteById(int id);

}
