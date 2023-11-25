package com.proyecto.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.demo.model.Categorias;
import com.proyecto.demo.repository.CategoriasRepository;

@Service
public class CategoriasServices {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public CategoriasServices(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    public Categorias save(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    public Iterable<Categorias> findAll() {
        return categoriasRepository.findAll();
    }

    public Categorias findById(int id) {
        return categoriasRepository.findById(id);
    }

    public void deleteById(int id) {
        categoriasRepository.deleteById(id);
    }
    

}
