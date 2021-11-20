
package com.FacuEgg.libreria.servicios;

import com.FacuEgg.libreria.entidades.Editorial;
import com.FacuEgg.libreria.errores.ErrorServicio;
import com.FacuEgg.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void registrar(String nombre) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("DEBE INDICAR UN NOMBRE");
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        editorialRepositorio.save(editorial);
    }
    
    @Transactional
    public void modificar(String id, String nombre) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("NO EXISTE LA EDITORIAL");
        }
    }
    
    @Transactional
    public void habilitar(String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("NO EXISTE LA EDITORIAL");
        }
    }
    
    @Transactional
    public void deshabilitar(String id) throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("NO EXISTE LA EDITORIAL");
        }
    }
        
    public List<Editorial> listar(){
        return editorialRepositorio.findAll();
    }
    
}
