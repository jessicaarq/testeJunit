package com.fatec.cadastrar;

import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class REQ01CadastrarAluno {
    @Autowired
    AlunoRepository repository;

    Aluno aluno;
    private Validator validator;
    private ValidatorFactory validatorFactory;

    @Test
    void ct01_quando_dados_validos_ra_cadastrado_retorna1() {
// Dado – que o atendente tem um aluno não cadastrado
        repository.deleteAll();
// Quando – o atendente cadastra o aluno
        Aluno aluno = new Aluno(20201234, "Joselito Almeida", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        repository.save(aluno);
        Aluno aluno2 = new Aluno();
        aluno2 = repository.findByRa(20201234);
// Então – o sistema verifica os dados E confirma a operação
        assertEquals(1, repository.count());
    }

    @Test
    void Ct02_Cadastrar_aluno_com_nome_em_branco(){
        // Dado – existem regras para validacao da entrada de dados
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        repository.deleteAll();
        // Quando – o usuário nao informa o nome e confirma a operação
        Aluno aluno = new Aluno(20201234, "", "joselito@gamail.com", 01221010, "rua do paraiso, 100" );
        Set<ConstraintViolation<Aluno>> violations = validator.validate(aluno);
        // Então – o sistema valida as informações E retorna mensagem titulo invalido.
        assertEquals("Nome deve ter entre 1 e 50 caracteres", violations.iterator().next().getMessage());
    }
}
