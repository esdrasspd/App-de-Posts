package com.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.proyecto.demo.model.Categorias;
import com.proyecto.demo.services.CategoriasServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoriasController {

    @Autowired
    private final CategoriasServices categoriasServices;

    public CategoriasController(CategoriasServices categoriasServices) {
        this.categoriasServices = categoriasServices;
    }

    @RequestMapping(value = "/categorias")
    public String getCategorias(Model model) {
        Iterable<Categorias> categorias = categoriasServices.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSelected", categorias);
        return "viewCategorias";
    }

    @RequestMapping(value = "/categorias/add")
    public String addCategorias(Model model) {
        return "viewSaveCategorias";
    }

    @PostMapping(value = "/findCategoriaById")
    public String findCategoriaById(Model model,
            @RequestParam(value = "id", defaultValue = "0") Integer id) {
                System.out.println(id);
        Categorias categoria = categoriasServices.findById(id);
        model.addAttribute("categorias", categoria);
        Iterable<Categorias> categoriasSelected = categoriasServices.findAll();
        model.addAttribute("categoriaSelected", categoriasSelected);
        model.addAttribute("id", id);
        return "viewCategorias";
    }

    @PostMapping(value = "/categorias/save")
    public String saveCategorias(Model model,
            @RequestParam(value = "nombre", defaultValue = "empty") String nombre) {
        categoriasServices.save(new Categorias(nombre));

        return "redirect:/categorias";
    }

    @GetMapping("/deleteCategorias{id}")
    public String deleteCategorias(@PathVariable int id) {
        categoriasServices.deleteById(id);
        return "redirect:/categorias";
    }

    @GetMapping("/editCategoria/{id}")
    public String editCategorias(@PathVariable int id, Model model) {
        Categorias categoria = categoriasServices.findById(id);
        model.addAttribute("categoria", categoria);
        return "viewEditCategorias";
    }

    @PostMapping("/editCategoria/{id}")
    public String updateAuthor(@PathVariable int id, @ModelAttribute Categorias categorias) {

        categoriasServices.save(categorias);
        return "redirect:/categorias";
    }

}
