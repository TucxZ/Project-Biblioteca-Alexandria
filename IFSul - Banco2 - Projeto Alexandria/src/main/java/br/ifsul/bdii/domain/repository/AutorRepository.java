package br.ifsul.bdii.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import br.ifsul.bdii.domain.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository
<Autor, Long>{
   
    @Query(" select a from Autor a where a.nome like%:name%")
    List<Autor> findByNameLike(@Param("name")String name);
    
    @Query(" select a from Autor a where a.dataNascimento>:dateBirth")
    List<Autor> findByDateBirth(@Param("dateBirth") Date dateBirth);

    @Query(" select a from Autor a where a.dataMorte>:dateDeath")
    List<Autor> findByDateDeath(@Param("dateDeath") Date dateDeath);

    @Query(" select a from Autor a where a.nacionalidade like:nation")
    List<Autor> findByNat (@Param("nation") String nation);

}
