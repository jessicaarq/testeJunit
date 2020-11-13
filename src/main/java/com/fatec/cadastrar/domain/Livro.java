package com.fatec.cadastrar.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 4, max = 4, message = "ISBN deve ter 4 caracteres")
    @Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
    private String isbn;
    @NotNull
    @Size(min = 1, max = 50, message = "Titulo deve ter entre 1 e 50 caracteres")
    private String titulo;
    @NotNull
    @Size(min = 1, max = 50, message = "Autor deve ter entre 1 e 50 caracteres")
    private String autor;

    public Livro() {

    }

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

}
