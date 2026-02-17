package org.company.books.backend.controllers;

import org.company.books.backend.Service.ICategoriaService;
import org.company.books.backend.model.Categoria;
import org.company.books.backend.response.CategoriaResponseRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriaRestControllerTest {

     @InjectMocks
     private CategoriaRestController categoriaRestController;

     @Mock
     private ICategoriaService service;

     @BeforeEach
     public void init(){
         MockitoAnnotations.openMocks(this);
     }

     @Test
     public void crearTest(){

          MockHttpServletRequest request =new MockHttpServletRequest();
          RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

          Categoria categoria=new Categoria(1L,"Libros de arte","Libros de historia del arte");

          Mockito.when(service.crear(Mockito.any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));

          ResponseEntity<CategoriaResponseRest> response=categoriaRestController.crear(categoria);

          assertThat(response.getStatusCode().value()).isEqualTo(200);
     }


}
