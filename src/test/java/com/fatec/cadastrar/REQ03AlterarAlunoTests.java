package com.fatec.cadastrar;

import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class REQ03AlterarAlunoTests {

    @Autowired
    AlunoRepository repository;

    @Test
    public void ct01_alterar_aluno(){
        //Como – atendente da biblioteca
        repository.deleteAll();
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        repository.save(aluno);
        //Eu quero – alterar as informações de um aluno
        aluno.setNome("Jose Almeida");
        repository.save(aluno);
        //De maneira que – as informações do aluno fiquem disponíveis para solicitação de empréstimo com as informações correta
        assertEquals("Jose Almeida", aluno.getNome());
    }
}
