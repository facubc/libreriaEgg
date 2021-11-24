package com.FacuEgg.libreria.servicios;

import com.FacuEgg.libreria.entidades.Autor;
import com.FacuEgg.libreria.errores.ErrorServicio;
import com.FacuEgg.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void registrar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("DEBE INDICAR UN NOMBRE");
        }
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);

        autorRepositorio.save(autor);
    }
    
    @Transactional
    public void modificar(String id, String nombre) throws ErrorServicio {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServicio("DEBE INDICAR UN NOMBRE");
            }
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("NO EXISTE EL AUTOR");
        }
    }
    
    @Transactional
    public void habilitar(String id) throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("NO EXISTE EL AUTOR");
        }
    }
    
    @Transactional
    public void deshabilitar(String id) throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("NO EXISTE EL AUTOR");
        }
    }
    
    
    public List<Autor> listar(){
        return autorRepositorio.findAll();
    }
    
    public Autor getOne(String id){
        return autorRepositorio.getById(id);
    }

}
