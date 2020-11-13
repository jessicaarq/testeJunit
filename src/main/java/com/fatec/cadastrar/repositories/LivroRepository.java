package com.fatec.cadastrar.repositories;

import com.fatec.cadastrar.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
    public Livro findByIsbn(@Param("isbn") String isbn);

    List<Livro> findAllByTituloIgnoreCaseContaining (String titulo);

    @Query(value = "SELECT l FROM Livro l ORDER BY titulo")
    List<Livro> buscaTodosLivrosOrdenadosPorTitulo();

}