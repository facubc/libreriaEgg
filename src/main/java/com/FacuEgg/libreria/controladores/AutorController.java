package com.FacuEgg.libreria.controladores;

import com.FacuEgg.libreria.entidades.Autor;
import com.FacuEgg.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/")
    public String autor(ModelMap modelo) {
        List<Autor> listAutor = autorServicio.listar();
        modelo.addAttribute("autores", listAutor);
        return "autor";
    }

    @GetMapping("/carga")
    public String formulario() {
        return "autor_form";
    }

    @PostMapping("/carga")
    public String carga(ModelMap modelo, @RequestParam String nombre) {
        try {
            autorServicio.registrar(nombre);
            modelo.put("exito", "Registro Exitoso");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "autor_form";
        }
        return "autor_form";
    }
    
    @GetMapping("/modificar")
    public String formModificar(){
        return"autor_modificar";
    }

    @PutMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre) {
        try {
            autorServicio.modificar(id, nombre);
            modelo.put("exito", "se modifico correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "";
        }
        return "";
    }

}
