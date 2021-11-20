
package com.FacuEgg.libreria.controladores;

import com.FacuEgg.libreria.entidades.Editorial;
import com.FacuEgg.libreria.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/")
    public String editorial(ModelMap modelo){
        List<Editorial> listEditoriales = editorialServicio.listar();
        modelo.addAttribute("editoriales", listEditoriales);
        return "editorial";
    }
    
    @GetMapping("/carga")
    public String formulario(){
        return "editorial_form";
    }
    
    @PostMapping("/carga")
    public String carga(ModelMap modelo, @RequestParam String nombre){
        try {
            editorialServicio.registrar(nombre);
            modelo.put("exito","registro exitoso");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "editorial_form";
        }
        return "index";
    }
    
    
    
}
