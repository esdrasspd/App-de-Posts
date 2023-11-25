package com.proyecto.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.demo.model.Etiquetas;

public interface EtiquetasRepository extends CrudRepository<Etiquetas, Integer> {
    
        //save
        Etiquetas save(Etiquetas etiquetas);
    
        //findAll
        Iterable<Etiquetas> findAll();
    
        //findById
        Etiquetas findById(int id);
    
        //delete
        void deleteById(int id);
    
}
