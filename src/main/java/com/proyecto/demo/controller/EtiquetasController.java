package com.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.demo.model.Etiquetas;
import com.proyecto.demo.services.EtiquetasServices;

@Controller
public class EtiquetasController {

    @Autowired
    private final EtiquetasServices etiquetasServices;

    public EtiquetasController(EtiquetasServices etiquetasServices) {
        this.etiquetasServices = etiquetasServices;
    }

    @RequestMapping(value = "/etiquetas")
    public String getEtiquetas(Model model) {
        Iterable<Etiquetas> etiquetas = etiquetasServices.findAll();
        model.addAttribute("etiquetas", etiquetas);
        model.addAttribute("etiquetasSelected", etiquetas);
        return "viewEtiquetas";
    }

    @RequestMapping(value = "/etiquetas/add")
    public String addEtiquetas(Model model) {
        return "viewSaveEtiquetas";
    }

    @PostMapping(value = "/findEtiquetaById")
    public String findEtiquetaById(Model model,
            @RequestParam(value = "id", defaultValue = "0") Integer id) {
        Etiquetas etiqueta = etiquetasServices.findById(id);
        model.addAttribute("etiquetas", etiqueta);
        Iterable<Etiquetas> etiquetasSelected = etiquetasServices.findAll();
        model.addAttribute("etiquetasSelected", etiquetasSelected);
        model.addAttribute("id", id);
        return "viewEtiquetas";
    }

    @PostMapping(value = "/etiqueta/save")
    public String saveEtiquetas(Model model,
            @RequestParam(value = "nombre", defaultValue = "empty") String nombre) {
        etiquetasServices.save(new Etiquetas(nombre));

        return "redirect:/etiquetas";
    }

    @GetMapping("/deleteEtiqueta{id}")
    public String deleteEtiquetas(@PathVariable int id) {
        etiquetasServices.deleteById(id);
        return "redirect:/etiquetas";
    }

    @GetMapping("/editEtiqueta/{id}")
    public String editEtiquetas(@PathVariable int id, Model model) {
        Etiquetas etiqueta = etiquetasServices.findById(id);
        model.addAttribute("etiqueta", etiqueta);
        return "viewEditEtiquetas";
    }

    @PostMapping("/editEtiqueta/{id}")
    public String updateEtiqueta(@PathVariable int id, @ModelAttribute Etiquetas etiqueta) {

        etiquetasServices.save(etiqueta);
        return "redirect:/etiquetas";
    }
}
