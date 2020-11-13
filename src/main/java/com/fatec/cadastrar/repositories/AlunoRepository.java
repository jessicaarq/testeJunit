package com.fatec.cadastrar.repositories;

import com.fatec.cadastrar.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT l FROM Aluno l WHERE l.ra = :ra")
    public Aluno findByRa(@Param("ra") int ra);

    @Query("SELECT l FROM Aluno l WHERE l.nome LIKE %:nome%")
    public Aluno findByNome(@Param("nome") String nome);

}
