package br.ifsul.bdii.service;

import java.sql.Date;
import java.util.List;
import br.ifsul.bdii.domain.entity.Autor;

public interface AutorService {
    
    Autor findById(Long id);
    List<Autor> findByNameLike(String name);
    List<Autor> findByDateBirth(Date dateBirth);
    List<Autor> findByDateDeath(Date dateDeath);
    List<Autor> findByNat(String nation);
    List<Autor> findAll();
    Autor save(Autor autor);
    void update(Autor autor, Long id);
    void delete(Long id);
}
