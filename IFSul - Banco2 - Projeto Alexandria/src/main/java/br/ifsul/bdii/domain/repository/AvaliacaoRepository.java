package br.ifsul.bdii.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import br.ifsul.bdii.domain.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository
<Avaliacao, Long>{

    @Query(" select a from Avaliacao a join a.livro l where l.id=:id")
    List<Avaliacao> findByLivroId(@Param("id") Long id);

}