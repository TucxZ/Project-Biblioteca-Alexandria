package br.ifsul.bdii.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import br.ifsul.bdii.domain.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository
<Livro, Long>{

    @Query(" select l from Livro l where l.titulo like %:name%")
    List<Livro> findByNameLike(@Param("name")String name);

    @Query(" select l from Livro l where l.descricao like %:descrip%")
    List<Livro> findByDescLike(@Param("descrip")String descrip);
}