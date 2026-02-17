package org.company.books.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name= "libros")
@JsonPropertyOrder({ "id", "nombre", "descripcion","categoria" })
public class Libros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY) //Índica la association entre las dos tablas de la tablas en la base de dates
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"}) //omite estos dos campos en el json para que a la hora de convertir la clase a json no de error
    private  Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
