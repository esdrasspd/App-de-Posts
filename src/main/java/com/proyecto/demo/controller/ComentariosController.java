package com.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.demo.model.Comentarios;
import com.proyecto.demo.model.Posts;
import com.proyecto.demo.services.ComentariosServices;
import com.proyecto.demo.services.PostsServices;

@Controller
public class ComentariosController {

    @Autowired
    private final ComentariosServices comentariosServices;
    private final PostsServices postsServices;

    public ComentariosController(ComentariosServices comentariosServices, PostsServices postsServices) {
        this.comentariosServices = comentariosServices;
        this.postsServices = postsServices;
    }

    @RequestMapping("/comentarios")
    public String getComentarios(Model model) {
        Iterable<Comentarios> comentarios = comentariosServices.findAll();
        Iterable<Posts> posts = postsServices.findAll();
        model.addAttribute("posts" , posts);
        model.addAttribute("postSelected", posts);
        model.addAttribute("comentarios", comentarios);
        return "viewComentarios";
    }

    @PostMapping("/findComentarios")
    public String findComentarios(
            @RequestParam(value = "id", defaultValue = "0") Integer id,
            @RequestParam(value = "idPost", defaultValue = "0") Integer idPost,
            Model model) {
                System.out.println("id: " + id + " idPost: " + idPost);
        Iterable<Comentarios> comentario = comentariosServices.findComentariosByPostsId(id, idPost);
        Iterable<Posts> posts = postsServices.findAll();
        model.addAttribute("posts" , posts);
        model.addAttribute("postSelected", posts);
        model.addAttribute("comentarios", comentario);
        model.addAttribute("id", id);
        model.addAttribute("idPost", idPost);
        return "viewComentarios";
    }

    @RequestMapping("/addComentarios")
    public String addComentarios(Model model) {
        return "viewSaveComentarios";
    }

    @PostMapping("/findComentarioById")
    public String findAuthorById(
            @RequestParam(value = "id", defaultValue = "0") Integer id,
            Model model) {
        Comentarios comentario = comentariosServices.findById(id);
        model.addAttribute("comentario", comentario);
        Iterable<Comentarios> comentarios = comentariosServices.findAll();
        model.addAttribute("comentarioSelected", comentarios);
        model.addAttribute("id", id);
        return "viewAuthors";
    }

    @PostMapping("/save/Comentarios")
    public String saveComentario(
            @RequestParam(value = "cuerpo_comentario", defaultValue = "empty") String cuerpo,
            @RequestParam(value ="post_id") Posts post) {

        comentariosServices.save(new Comentarios(cuerpo, post));
        return "redirect:/comentarios";
    }

    @ModelAttribute("post")
    public Iterable<Posts> posts() {
        return postsServices.findAll();
    }

    
    @GetMapping("/deleteComentario/{id}")
    public String deleteComentario(@PathVariable int id) {
        comentariosServices.deleteById(id);
        return "redirect:/comentarios";
    }
    

    @GetMapping("/editComentario/{id}")
    public String editComentario(@PathVariable int id, Model model) {
        Comentarios comentario = comentariosServices.findById(id);
        model.addAttribute("comentario", comentario);
        return "viewEditComentarios";
    }

    @PostMapping("/editComentario/{id}")
    public String updateAuthor(@PathVariable int id, @ModelAttribute Comentarios comentario) {

        comentariosServices.save(comentario);
        return "redirect:/comentarios";
    }

}
