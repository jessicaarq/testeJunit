package com.fatec.cadastrar.service;

import com.fatec.cadastrar.domain.Aluno;
import com.fatec.cadastrar.repositories.AlunoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repo;

    public List<Aluno> findAll(){
        return repo.findAll();
    }

    public Aluno find(Long id) {
        Optional<Aluno> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id, "Objeto não encontrado, Tipo: " + Aluno.class.getName()));
    }

    public Aluno insert (Aluno obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Aluno update (Aluno obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete (Long id) {
        find(id);
        repo.delete(find(id));
    }
}
