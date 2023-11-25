package com.proyecto.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cuerpo_comentario")
    private String cuerpoComentario;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    public Comentarios() {
    }

    public Comentarios(String cuerpoComentario, Posts posts) {
        super();
        this.cuerpoComentario = cuerpoComentario;
        this.posts = posts;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCuerpoComentario(String cuerpoComentario) {
        this.cuerpoComentario = cuerpoComentario;
    }

    public String getCuerpoComentario() {
        return this.cuerpoComentario;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Posts getPosts() {
        return this.posts;
    }

    //toString
    @Override
    public String toString() {
        return "Comentarios [id=" + id + ", cuerpoComentario=" + cuerpoComentario + ", posts=" + posts + "]";
    }

}
