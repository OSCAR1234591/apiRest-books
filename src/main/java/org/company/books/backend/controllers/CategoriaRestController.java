package org.company.books.backend.controllers;

import org.company.books.backend.Service.ICategoriaService;
import org.company.books.backend.model.Categoria;
import org.company.books.backend.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = {"http://localhost:4200"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService service;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCat(){

        ResponseEntity<CategoriaResponseRest> response = service.buscarCategoria();

        return response;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarPorId(@PathVariable Long id){
        
        ResponseEntity<CategoriaResponseRest> response =service.buscarPorId(id);

        return response;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){

        ResponseEntity<CategoriaResponseRest> response = service.crear(request);

        return response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request,@PathVariable Long id){

        ResponseEntity<CategoriaResponseRest> response = service.actualizar(request, id);

        return response;
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> elimimar(@PathVariable Long id){

        ResponseEntity<CategoriaResponseRest> response = service.eliminar(id);

        return response;

    }

}
