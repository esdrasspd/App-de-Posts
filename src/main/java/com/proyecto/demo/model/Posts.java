package com.proyecto.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @Column(name= "fecha_publicacion")
    private String fechaPublicacion;

    private String contenido;

    @ManyToOne
    @JoinColumn(name= "categoria_id")
    private Categorias categoria;

    @ManyToOne
    @JoinColumn(name ="etiqueta_id")
    private Etiquetas etiquetas;

    public Posts() {
    }

    public Posts(String titulo, String fechaPublicacion, String contenido,
            Categorias categoria, Etiquetas etiquetas) {
        super();
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Categorias getCategoria() {
        return this.categoria;
    }

    public void setEtiquetas(Etiquetas etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Etiquetas getEtiquetas() {
        return this.etiquetas;
    }


    //toString con etiquetas
    @Override
    public String toString() {
        return "Posts [id=" + id + ", titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion + ", contenido="
                + contenido + ", categoria=" + categoria + ", etiquetas=" + etiquetas
                + "]";
    }

}
