package com.proyecto.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.demo.model.Etiquetas;
import com.proyecto.demo.model.Posts;
import com.proyecto.demo.repository.ComentariosRepository;
import com.proyecto.demo.repository.PostsRepository;

@Service
public class PostsServices {
    @Autowired
    private PostsRepository postsRepository;
    private ComentariosRepository comentariosRepository;

    public PostsServices(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Posts postsSave(Posts posts) {
        return postsRepository.save(posts);
    }

    public Iterable<Posts> findAll() {
        return postsRepository.findAll();
    }

    public Posts findById(int id) {
        return postsRepository.findById(id);
    }

    public void deletePostAndComentario(int id) {
        comentariosRepository.deleteByPosts_Id(id);
        postsRepository.deleteById(id);
    }

    public List<Posts> findPosts(Integer id, Integer categoriaId, Integer etiqueta) {
        return postsRepository.findPosts(id, categoriaId, etiqueta);
    }

}
