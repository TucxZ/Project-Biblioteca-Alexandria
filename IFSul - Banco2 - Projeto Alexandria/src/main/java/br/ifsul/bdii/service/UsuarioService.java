package br.ifsul.bdii.service;

import java.util.List;

import br.ifsul.bdii.domain.entity.Usuario;

public interface UsuarioService{

    Usuario findById(Long id);
    Usuario findByNameOrEmail(String string);
    List<Usuario> findByEmailLike(String email);
    List<Usuario> findByNameLike(String name);
    List<Usuario> findIfAlert();
    List<Usuario> findAll();
    Usuario save (Usuario usuario);
    void update (Usuario usuario, Long id);
    void delete (Long id);

}