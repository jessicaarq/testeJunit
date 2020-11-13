package com.fatec.cadastrar;

import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class REQ04ExcluirAlunoTests {
    @Autowired
    AlunoRepository repository;

    @Test
    public void ct01_excluir_aluno(){
        // Dado – que o aluno está cadastrado
        repository.deleteAll();
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        repository.save(aluno);
        // Quando – o atendente solicita exclusao
        Aluno aluno2 = repository.findByRa(20201234);
        repository.deleteById(aluno2.getId());
        // Então – o resultado obtido da consulta deve ser null
        assertThat(repository.findByRa(20201234)).isEqualTo(null);
    }
}
