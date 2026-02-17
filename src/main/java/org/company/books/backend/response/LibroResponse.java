package org.company.books.backend.response;

import org.company.books.backend.model.Libros;

import java.util.List;

public class LibroResponse {

    private List<Libros> listaDeLibros;

    public List<Libros> getListaDeLibros() {
        return listaDeLibros;
    }

    public void setListaDeLibros(List<Libros> listaDeLibros) {
        this.listaDeLibros = listaDeLibros;
    }
}
