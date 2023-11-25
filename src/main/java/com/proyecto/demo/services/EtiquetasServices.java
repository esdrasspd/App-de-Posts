package com.proyecto.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.demo.model.Etiquetas;
import com.proyecto.demo.repository.EtiquetasRepository;

@Service
public class EtiquetasServices {

    @Autowired
    private EtiquetasRepository etiquetasRepository;

    public EtiquetasServices(EtiquetasRepository etiquetasRepository) {
        this.etiquetasRepository = etiquetasRepository;
    }

    public Etiquetas save(Etiquetas etiquetas) {
        return etiquetasRepository.save(etiquetas);
    }

    public Iterable<Etiquetas> findAll() {
        return etiquetasRepository.findAll();
    }

    public Etiquetas findById(int id) {
        return etiquetasRepository.findById(id);
    }

    public void deleteById(int id) {
        etiquetasRepository.deleteById(id);
    }
    
}
