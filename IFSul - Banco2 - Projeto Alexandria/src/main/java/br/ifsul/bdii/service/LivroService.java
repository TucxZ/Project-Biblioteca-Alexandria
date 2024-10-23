package br.ifsul.bdii.service;

import java.util.List;
import br.ifsul.bdii.domain.entity.Livro;

public interface LivroService{

    Livro findById(Long id);
    List<Livro> findByTituloLike(String name);
    List<Livro> findByDescLike(String descrip);
    List<Livro> findAll();
    Livro save (Livro livro);
    void update (Livro livro, Long id);
    void delete (Long id);

}