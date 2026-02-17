package org.company.books.backend.model.dao;

import org.company.books.backend.model.Libros;
import org.springframework.data.repository.CrudRepository;

public interface ILibrosDAO extends CrudRepository<Libros,Long> {
}
