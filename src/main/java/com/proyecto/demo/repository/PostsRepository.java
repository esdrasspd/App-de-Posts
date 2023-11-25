package com.proyecto.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyecto.demo.model.Etiquetas;
import com.proyecto.demo.model.Posts;

public interface PostsRepository extends CrudRepository<Posts, Integer> {
    //save
    Posts save(Posts posts);

    //findAll
    Iterable<Posts> findAll();

    //findById
    Posts findById(int id);

    //delete
    void deleteById(int id);

    @Query("SELECT p FROM Posts p " +
       "WHERE (:id = 0 OR p.id = :id) AND (:categoriaId = 0 OR p.categoria.id = :categoriaId) " +
       "AND (:etiqueta = 0 OR p.etiquetas.id = :etiqueta)")
    List<Posts> findPosts(@Param("id") Integer id, 
                                  @Param("categoriaId") Integer categoriaId,
                                  @Param("etiqueta") Integer etiqueta);

}
