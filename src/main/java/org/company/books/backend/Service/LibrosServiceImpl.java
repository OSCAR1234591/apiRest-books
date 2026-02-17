package org.company.books.backend.Service;

import org.company.books.backend.model.Libros;
import org.company.books.backend.model.dao.ILibrosDAO;
import org.company.books.backend.response.LibroResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibrosServiceImpl implements ILibrosService{

    private static final Logger log = LoggerFactory.getLogger(LibrosServiceImpl.class);

    @Autowired
    private ILibrosDAO service;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {

        LibroResponseRest response = new LibroResponseRest();

        List<Libros> listaDeLibros=new ArrayList<>();

        log.info("Iniciando metodo de buscarLibros()");
        try {

            listaDeLibros= (List<Libros>) service.findAll();
            response.getLibroResponse().setListaDeLibros(listaDeLibros);

            response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");


        }catch (Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar buscarLibros() : ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        log.info("Finalizando metodo de buscarLibros()");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> bucarLibrosPorId(Long id) {

        LibroResponseRest response = new LibroResponseRest();
        List<Libros> listaDeLibros=new ArrayList<>();

        log.info("Iniciando metodo de bucarLibrosPorId()");
        try {

            Optional<Libros> libro =service.findById(id);
            if (libro.isPresent()){
                listaDeLibros.add(libro.get());
                response.getLibroResponse().setListaDeLibros(listaDeLibros);
                response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");
            }else{
                log.error("error al consultar libros: ");
                response.setMetaData("Respuesta no ok", "400", "Libro no encontrado");
                return new  ResponseEntity<LibroResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar bucarLibrosPorId() : ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Finalizando metodo de bucarLibrosPorId()");
        return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> crearLibros(Libros libros) {

        LibroResponseRest response = new LibroResponseRest();
        List<Libros> listaDeLibros=new ArrayList<>();

        log.info("Iniciando metodo de crear libro");
        try {
            Libros librosCreado = service.save(libros);
            if (libros !=null){
                listaDeLibros.add(libros);
                response.getLibroResponse().setListaDeLibros(listaDeLibros);
                response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");
            }else {
                log.error("error al consultar crear el libro:");
                response.setMetaData("Respuesta no ok", "200", "Libro no creado");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error en el metodo crear() : ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> modificarLibros(Libros libros, Long id) {

        LibroResponseRest response =new LibroResponseRest();
        List<Libros> listaDeLibros=new ArrayList<>();
        log.info("Iniciando metodo de modificar libros");
        try {

            Optional<Libros> libroRecuperado=service.findById(id);
            if (libroRecuperado.isPresent()){

                libroRecuperado.get().setNombre(libros.getNombre());
                libroRecuperado.get().setDescripcion(libros.getDescripcion());
                libroRecuperado.get().setCategoria(libros.getCategoria());

                listaDeLibros.add(service.save(libroRecuperado.get()));
                response.getLibroResponse().setListaDeLibros(listaDeLibros);

                response.setMetaData("Respues OK","200","Libro modificado correctamente");
            }else {
                log.info("Error al modificar el libro");
                response.setMetaData("Repuesta no ok","400", "Repuesta incorrecta");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error en el metodo modificar() : ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminarLibros(Long id) {

        LibroResponseRest response =new LibroResponseRest();
        List<Libros> listaDeLibros=new ArrayList<>();

        log.info("Iniciando metodo de eliminarLibros libros");
        try {
            Optional<Libros> libroRecuperado=service.findById(id);
            if (libroRecuperado.isPresent()){
                service.deleteById(id);
                listaDeLibros.add(libroRecuperado.get());
                response.getLibroResponse().setListaDeLibros(listaDeLibros);
                response.setMetaData("Respues OK","200","Libro eliminado correctamente");
            }else {
                log.info("Error el libro no se a podido eliminar correctamente ");
                response.setMetaData("Repuesta no ok","400", "Repuesta incorrecta");
                return new ResponseEntity<LibroResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error en el metodo eliminarLibros() : ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
    }

}
