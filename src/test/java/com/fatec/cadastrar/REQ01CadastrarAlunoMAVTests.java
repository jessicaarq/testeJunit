package com.fatec.cadastrar;

import com.fatec.cadastrar.domain.Aluno;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class REQ01CadastrarAlunoMAVTests {

    @Autowired
    MockMvc mockMvc;// simula o processamento de uma requisicao web

    @Test
    public void ct01_quando_seleciona_cadastrar_livro_retorna_http_200() throws Exception{
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/cadastrar"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ct01_quando_seleciona_consultar_aluno_retorna_http_200() throws Exception{
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/consultar"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ct02_quando_seleciona_cadastrar_retorna_view() throws Exception {
        // Dado – que o sistema está disponível
        // Quando – o usuário faz uma solicitação do tipo GET localhost:8080/alunos/cadastrar
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/cadastrar"));
        ViewResultMatchers view = MockMvcResultMatchers.view();
        // Então – retorna o nome logico da view de cadastro de livro
        resultActions.andExpect(view.name(Matchers.is("cadastrarAluno")));
    }

    @Test
    public void ct03_quando_titulo_branco_retorna_size() throws Exception {
        //Dado – que o livro nao esta cadastrado
        //Quando - o usuario deixa o titulo em branco
        mockMvc.perform(MockMvcRequestBuilders.post("/alunos/save") //simula uma requisicao post entrada pelo usuario
                .param("ra", "20201234")
                .param("nome", "")
                .param("email", "joselito@gamail.com")
                .param("cep", "01221010")
                .param("endereco", "rua do paraiso, 100")
        )
        //Então - retorna erro
                .andExpect(MockMvcResultMatchers.status().is(200)) //404
                .andExpect(MockMvcResultMatchers.view().name("cadastrarLivro"))
                .andExpect(MockMvcResultMatchers.model().attribute("aluno", Matchers.any(Aluno.class)))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("aluno","nome"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("aluno", "nome", "Size"));
    }
}
