package org.company.books.backend.model.dao;

import org.company.books.backend.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDAO extends CrudRepository<Categoria,Long>{

}
