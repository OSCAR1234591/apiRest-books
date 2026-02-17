package org.company.books.backend.controllers;

import org.company.books.backend.Service.ILibrosService;
import org.company.books.backend.model.Libros;
import org.company.books.backend.response.LibroResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LibroResponseRestController {

    @Autowired
    private ILibrosService service;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> getLibros(){

        ResponseEntity<LibroResponseRest> response = service.buscarLibros();

        return response;

    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> getLibrosPorId(@PathVariable Long id){

        ResponseEntity<LibroResponseRest> response = service.bucarLibrosPorId(id);

        return response;

    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crearLibros(@RequestBody Libros libros){

        ResponseEntity<LibroResponseRest> response = service.crearLibros(libros);

        return response;

    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> modificarLibros(@RequestBody Libros libros, @PathVariable Long id){

        ResponseEntity<LibroResponseRest> response = service.modificarLibros(libros,id);

        return response;

    }
    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> eliminarLibros(@PathVariable Long id){

        ResponseEntity<LibroResponseRest> response = service.eliminarLibros(id);

        return response;

    }

}
