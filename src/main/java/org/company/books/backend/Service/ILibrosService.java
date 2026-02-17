package org.company.books.backend.Service;

import org.company.books.backend.model.Libros;
import org.company.books.backend.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibrosService {

    public ResponseEntity<LibroResponseRest> buscarLibros();

    public  ResponseEntity<LibroResponseRest> bucarLibrosPorId(Long id);

    public ResponseEntity<LibroResponseRest> crearLibros(Libros libros);

    public ResponseEntity<LibroResponseRest> modificarLibros(Libros libros, Long id);

    public ResponseEntity<LibroResponseRest> eliminarLibros(Long id);

}
