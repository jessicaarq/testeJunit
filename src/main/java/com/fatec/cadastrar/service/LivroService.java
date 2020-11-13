package com.fatec.cadastrar.service;

import com.fatec.cadastrar.domain.Livro;
import com.fatec.cadastrar.repositories.LivroRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepository repo;

    public List<Livro> findAll(){
        return repo.findAll();
    }

    public Livro find(Long id) {
        Optional<Livro> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id, "Objeto não encontrado, Tipo: " + Livro.class.getName()));
    }

    public Livro insert (Livro obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Livro update (Livro obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete (Long id) {
        find(id);
        repo.delete(find(id));
    }
}
