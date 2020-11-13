package com.fatec.cadastrar;

import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class REQ02ConsultarAlunoTests {
    @Autowired
    AlunoRepository repository;

    @Test
    void ct01_consultar_por_ra() {
        // Dado – que o atendente tem um aluno cadastrado
        repository.deleteAll();
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        // Quando – o atendente consultar o aluno
        repository.save(aluno);
        // Então – o sistema verifica os dados E confirma a operação
        assertEquals(aluno, repository.findByRa(20201234));
    }

    @Test
    void ct02_onsultar_pelo_nome_completo(){
        // Dado – que existe o aluno está cadastrado
        repository.deleteAll();
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        // Quando – o atendente informa o nome para consulta
        repository.save(aluno);
        // Então – o sistema valida as informações E apresenta os dados do aluno
        assertEquals(aluno, repository.findByNome("Joselito Almeida"));
    }

    @Test
    void ct02_onsultar_pelo_nome_parcial_do_aluno(){
        // Dado – que existe o aluno está cadastrado
        repository.deleteAll();
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        // Quando – o atendente informa o nome para consulta
        repository.save(aluno);
        // Então – o sistema valida as informações E apresenta os dados do aluno
        assertEquals(aluno, repository.findByNome("Joselito"));
    }
}
