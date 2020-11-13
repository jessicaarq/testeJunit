package com.fatec.cadastrar.controller;


import com.fatec.cadastrar.domain.Livro;
import com.fatec.cadastrar.service.LivroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(path = "/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    Logger logger = LogManager.getLogger(LivroController.class);

    @GetMapping("/cadastrar")
    public ModelAndView retornaFormDeCadastroDe(Livro livro) {
        ModelAndView mv = new ModelAndView("cadastrarLivro");
        mv.addObject("livro", livro);
        return mv;
    }

    @RequestMapping(value="/consultar", method= RequestMethod.GET)
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/consultar/{id}", method= RequestMethod.GET)
    public ResponseEntity<Livro> find(@PathVariable Long id) {
        Livro obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/inserir", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Livro obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Livro livro, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("cadastrarLivro");
        try {
            service.insert(livro);
            modelAndView.addObject("livros", service.findAll());
            modelAndView.addObject("message", "Livro cadastrado");
        } catch (Exception e) { // captura validacoes na camada de persistencia
            if (result.hasErrors()) {
                logger.info("======================> entrada de dados invalida na pagina cadastrar livro");
                modelAndView.addObject("message", "");

            } else {
                modelAndView.addObject("message", "Livro ja cadastrado");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Livro obj){
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
