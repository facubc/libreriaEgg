package com.FacuEgg.libreria.servicios;

import com.FacuEgg.libreria.entidades.Autor;
import com.FacuEgg.libreria.entidades.Editorial;
import com.FacuEgg.libreria.entidades.Libro;
import com.FacuEgg.libreria.errores.ErrorServicio;
import com.FacuEgg.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional
    public void registrar(String titulo, Long isbn, Integer ejemplares, Integer ejemplaresPrestados, Autor autor, Editorial editorial) throws ErrorServicio {
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("DEBE INDICAR UN NOMBRE");
        }
        if (isbn == null || isbn == 0) {
            throw new ErrorServicio("DEBE INDICAR UN ISBN CORRECTO");
        }
        if (ejemplares == 0) {
            throw new ErrorServicio("DEBE INDICAR LA CANTIDAD A REGISTRAR");
        }
        if (ejemplaresPrestados > ejemplares) {
            throw new ErrorServicio("NO PUEDE PRESTAR MAS DE LO QUE TIENE");
        }
        if (autor == null) {
            throw new ErrorServicio("DEBE INDICAR UN AUTOR");
        }
        if (editorial == null) {
            throw new ErrorServicio("DEBE INDICAR UNA EDITORIAL");
        }

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
        libro.setAlta(true);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    @Transactional
    public void modificar(String id, String titulo, Long isbn, Integer ejemplares, Integer ejemplaresPrestados, Autor autor, Editorial editorial) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            if (titulo == null || titulo.isEmpty()) {
                throw new ErrorServicio("DEBE INDICAR UN NOMBRE");
            }
            if (isbn == null || isbn == 0) {
                throw new ErrorServicio("DEBE INDICAR UN ISBN CORRECTO");
            }
            if (ejemplares == 0) {
                throw new ErrorServicio("DEBE INDICAR LA CANTIDAD A REGISTRAR");
            }
            if (ejemplaresPrestados > ejemplares) {
                throw new ErrorServicio("NO PUEDE PRESTAR MAS DE LO QUE TIENE");
            }
            if (autor == null) {
                throw new ErrorServicio("DEBE INDICAR UN AUTOR");
            }
            if (editorial == null) {
                throw new ErrorServicio("DEBE INDICAR UNA EDITORIAL");
            }

            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("NO EXISTE EL LIBRO");
        }
    }
    
    @Transactional
    public void habilitar(String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);
        }else{
            throw new ErrorServicio("NO EXISTE LIBRO");
        }
    }
    
    @Transactional
    public void deshabilitar(String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);
        }else{
            throw new ErrorServicio("NO EXISTE LIBRO");
        }
    }
    
    public List<Libro> listar(){
        return libroRepositorio.findAll();
    }

}
