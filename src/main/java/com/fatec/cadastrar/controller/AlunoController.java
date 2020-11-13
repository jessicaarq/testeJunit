package com.fatec.cadastrar.controller;


import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(path = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    Logger logger = LogManager.getLogger(AlunoController.class);

    @RequestMapping("/cadastrar")
    public ModelAndView retornaFormDeCadastroDe(Aluno aluno) {
        ModelAndView mv = new ModelAndView("cadastrarAluno");
        mv.addObject("aluno", aluno);
        return mv;
    }

    @RequestMapping(value="/consultar", method= RequestMethod.GET)
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/consultar/{id}", method= RequestMethod.GET)
    public ResponseEntity<Aluno> find(@PathVariable Long id) {
        Aluno obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/inserir", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Aluno obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("cadastrarLivro");
        try {
            service.insert(aluno);
            modelAndView.addObject("alunos", service.findAll());
            modelAndView.addObject("message", "Livro cadastrado");
        } catch (Exception e) { // captura validacoes na camada de persistencia
            if (result.hasErrors()) {
                logger.info("======================> entrada de dados invalida na pagina cadastrar aluno");
                modelAndView.addObject("message", "");

            } else {
                modelAndView.addObject("message", "Livro ja cadastrado");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aluno obj){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
