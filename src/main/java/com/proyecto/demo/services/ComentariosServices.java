package com.proyecto.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.demo.model.Comentarios;
import com.proyecto.demo.repository.ComentariosRepository;

@Service
public class ComentariosServices {
    
    @Autowired
    private ComentariosRepository comentariosRepository;

    public ComentariosServices(ComentariosRepository comentariosRepository) {
        this.comentariosRepository = comentariosRepository;
    }

    public Comentarios save(Comentarios comentarios) {
        return comentariosRepository.save(comentarios);
    }

    public Iterable<Comentarios> findAll() {
        return comentariosRepository.findAll();
    }

    public Comentarios findById(int id) {
        return comentariosRepository.findById(id);
    }

    public void deleteById(int id) {
        comentariosRepository.deleteById(id);
    }

    public List<Comentarios> findComentariosByPostsId(Integer id, Integer posts_id) {
        //return comentariosRepository.findComentariosByPosts_Id(id, posts_id);
        return comentariosRepository.findComentariosByPosts_Id(posts_id);
    }
    
}
