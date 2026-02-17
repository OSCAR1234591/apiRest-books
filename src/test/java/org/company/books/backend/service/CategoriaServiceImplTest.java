package org.company.books.backend.service;

import org.company.books.backend.Service.CategoriaServiceImpl;
import org.company.books.backend.model.Categoria;
import org.company.books.backend.model.dao.ICategoriaDAO;
import org.company.books.backend.response.CategoriaResponseRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CategoriaServiceImplTest {

    @InjectMocks //Sé encargar de inyectar los Mock necesarios para el uso de esta clase
    private CategoriaServiceImpl service;

    @Mock // Simula la clase/Interface.... a la que apunta
    private ICategoriaDAO CategoriaDAO;

    private List<Categoria> listDeCategorias = new ArrayList<Categoria>();

    private Optional<Categoria> cat;

    @BeforeEach
    public void init(){
        cagarCategorias();
        cargarCategoria();
        MockitoAnnotations.openMocks(this);//Crea instancias de los Mocks
    }

    @Test
    public void buscarCategoriasTest(){

        when(CategoriaDAO.findAll()).thenReturn(listDeCategorias);
        ResponseEntity<CategoriaResponseRest> response = service.buscarCategoria();

        Assertions.assertEquals(4,response.getBody().getCategoriaResponse().getCategoria().size());

        verify(CategoriaDAO,times(1)).findAll();
    }

    @Test
    public void buscarCategoriaPorId(){

        when(CategoriaDAO.findById(1L)).thenReturn(cat);
        ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(1L);

        Assertions.assertEquals(1,response.getBody().getCategoriaResponse().getCategoria().size());

        verify(CategoriaDAO,times(1)).findById(1L);

    }

    private void cargarCategoria(){
        cat = Optional.of( new Categoria (1L,"Libros de arte","Libros de historia del arte"));
    }

    private void cagarCategorias(){

        Categoria catUno =new Categoria(1L,"Libros de arte","Libros de historia del arte");
        Categoria catDos =new Categoria(2L,"Lácteos","Variedad de lácteos");
        Categoria catTres =new Categoria(3L,"Carnes","Variedad de carnes");
        Categoria catCuatro =new Categoria(4L,"Camisas","Variedad de libros");

        listDeCategorias.add(catUno);
        listDeCategorias.add(catDos);
        listDeCategorias.add(catTres);
        listDeCategorias.add(catCuatro);
    }


}
