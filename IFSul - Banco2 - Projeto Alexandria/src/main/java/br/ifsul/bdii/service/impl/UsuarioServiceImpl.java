package br.ifsul.bdii.service.impl;

import br.ifsul.bdii.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.ifsul.bdii.exception.ServiceRuleException;
import br.ifsul.bdii.domain.repository.UsuarioRepository;
import br.ifsul.bdii.domain.entity.Usuario;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private final UsuarioRepository repository;

    @Override
    public Usuario findById(Long id) {
        return repository.findById(
            id
        ).orElseThrow(() -> new ServiceRuleException("Id de usuário não encontrado."));
    }

    @Override
    public Usuario findByNameOrEmail(String string){
        return repository.findByNameOrEmail(
            string
            ).orElseThrow(() -> new ServiceRuleException("Usuário não encontrado."));
    }

    @Override
    public List<Usuario> findByEmailLike(String email) {
        return repository.findByEmailLike(email);
    }

    @Override
    public List<Usuario> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    @Override
    public List<Usuario> findIfAlert() {
        return repository.findIfAlert();
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void update(Usuario usuario, Long id) {
        
        repository.findById(id)
            .map(u -> {
                usuario.setId(id);
                repository.save(usuario);
                return u;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de usuário não encontrado."));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(u -> {
                repository.delete(u);
                return u;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de usuário não encontrado."));
    }

}
