package com.fatec.cadastrar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "aluno")
public class Aluno {

    public Aluno() {
    }

    public Aluno(int ra, String nome, String email, int cep, String endereco) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "ra", unique = true)
    private int ra;
    @NotNull
    @Size(min = 1, max = 50, message = "Nome deve ter entre 1 e 50 caracteres")
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "cep")
    private int cep;
    @NotNull
    @Size(min = 1, max = 200, message = "Endereco deve ter entre 1 e 200 caracteres")
    @Column(name = "endereco")
    private String endereco;


}
