package com.proyecto.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyecto.demo.model.Comentarios;

public interface ComentariosRepository extends CrudRepository<Comentarios, Integer> {

    //save
    Comentarios save(Comentarios comentarios);

    //findAll
    Iterable<Comentarios> findAll();

    //findById
    Comentarios findById(int id);

    //delete
    void deleteById(int id);

    //deleteByPostsId
    void deleteByPosts_Id(Integer id);

    //findComentariosByPostsId
    @Query("SELECT c FROM Comentarios c WHERE (:idPost = 0 OR c.posts.id = :idPost)")
    List<Comentarios> findComentariosByPosts_Id(@Param("idPost") Integer idPost);


}   
