package com.proyecto.demo.controller;

import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.demo.model.Categorias;
import com.proyecto.demo.model.Comentarios;
import com.proyecto.demo.model.Etiquetas;
import com.proyecto.demo.model.Posts;
import com.proyecto.demo.services.CategoriasServices;
import com.proyecto.demo.services.ComentariosServices;
import com.proyecto.demo.services.EtiquetasServices;
import com.proyecto.demo.services.PostsServices;

@Controller
public class PostsController {
    @Autowired
    private final PostsServices postsServices;
    private final CategoriasServices categoriasServices;
    private final EtiquetasServices etiquetasServices;
    private final ComentariosServices comentariosServices;
    
    public PostsController(PostsServices postsServices, CategoriasServices categoriasServices, EtiquetasServices etiquetasServices, ComentariosServices comentariosServices) {
        this.postsServices = postsServices;
        this.categoriasServices = categoriasServices;
        this.etiquetasServices = etiquetasServices;
        this.comentariosServices = comentariosServices;
    }

     @RequestMapping("/posts")
    public String getPosts(Model model){
        Iterable<Posts> posts = postsServices.findAll();
        Iterable<Categorias> categorias = categoriasServices.findAll();
        Iterable<Etiquetas> etiqueta = etiquetasServices.findAll();
        Iterable<Comentarios> comentarios = comentariosServices.findAll();

        model.addAttribute("posts", posts);
        model.addAttribute("postSelected", posts);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSelected", categorias);
        model.addAttribute("etiquetas", etiqueta);
        model.addAttribute("etiquetaSelected", etiqueta);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("comentarioSelected", comentarios);
        return "viewPosts";
    }

    @PostMapping("/findPosts")
    public String findPosts(
        @RequestParam(value = "id", defaultValue = "0") Integer id,
        @RequestParam(value = "categoriaId", defaultValue = "0") Integer categoriaId,
        @RequestParam(value = "etiquetasId", defaultValue = "0") Integer etiqueta, Model model
    ){
        Iterable<Posts> postSelected = postsServices.findAll();
        Iterable<Posts> posts = postsServices.findPosts(id, categoriaId, etiqueta);
        Iterable<Categorias> categorias = categoriasServices.findAll();
        Iterable<Etiquetas> etiquetas = etiquetasServices.findAll();

        model.addAttribute("posts", posts);
        model.addAttribute("postSelected", postSelected);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSelected", categorias);
        model.addAttribute("etiquetas", etiquetas);
        model.addAttribute("etiquetaSelected", etiquetas);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("etiquetasId", etiqueta);
        model.addAttribute("id", id);
        return "viewPosts";
    }

    @RequestMapping("/addPosts")
    public String addPosts(Model model){
        return "viewSavePost";
    }

    @PostMapping("/savePosts")
    public String savePosts(
        @RequestParam(name = "titulo", defaultValue = "empty") String titulo,
        @RequestParam(name = "fechaPublicacion", defaultValue = "empty") String fechaPublicacion,
        @RequestParam(name = "contenido", defaultValue = "empty") String contenido,
        @RequestParam(name = "categoriaId") Categorias categoriaId,
        @RequestParam(name = "etiquetaId") Etiquetas etiquetaId
    ){
        postsServices.postsSave(new Posts(titulo, fechaPublicacion, contenido, categoriaId, etiquetaId));
        return "redirect:/posts";
    }

    @ModelAttribute("categorias")
    public Iterable<Categorias> categorias(){
        return categoriasServices.findAll();
    }

    @ModelAttribute("etiquetas")
    public Iterable<Etiquetas> etiquetas(){
        return etiquetasServices.findAll();
    }

    @GetMapping("/deletePosts{id}")
    public String deletePosts(@PathVariable int id){
        postsServices.deletePostAndComentario(id);
        return "redirect:/posts";
    }

    @GetMapping("/editPosts/{id}")
    public String editPosts(@PathVariable int id, Model model){
        Posts posts = postsServices.findById(id);
        model.addAttribute("post", posts);
        return "viewEditPosts";
    }

    @PostMapping("/editPosts/{id}")
    public String updatePosts(@PathVariable int id, @ModelAttribute Posts posts){
        postsServices.postsSave(posts);
        return "redirect:/posts";
    }

}
