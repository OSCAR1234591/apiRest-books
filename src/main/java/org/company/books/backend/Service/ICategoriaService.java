package org.company.books.backend.Service;

import org.company.books.backend.model.Categoria;
import org.company.books.backend.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {

    public ResponseEntity<CategoriaResponseRest> buscarCategoria(); 

    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);

    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria,Long id);

    public ResponseEntity<CategoriaResponseRest> eliminar(Long id);

}
