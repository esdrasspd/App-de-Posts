package com.proyecto.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts_etiquetas")
public class PostsEtiquetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Posts postId;

    @ManyToOne
    private Etiquetas etiquetaId;

    public PostsEtiquetas() {
    }

    public PostsEtiquetas(Posts postId, Etiquetas etiquetaId) {
        this.postId = postId;
        this.etiquetaId = etiquetaId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public Posts getPostId() {
        return this.postId;
    }

    public void setEtiquetaId(Etiquetas etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public Etiquetas getEtiquetaId() {
        return this.etiquetaId;
    }

    //toString
    @Override
    public String toString() {
        return "PostsEtiquetas [id=" + id + ", postId=" + postId + ", etiquetaId=" + etiquetaId + "]";
    }
}

