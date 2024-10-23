package br.ifsul.bdii.service.impl;

import br.ifsul.bdii.service.LivroService;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.ifsul.bdii.exception.ServiceRuleException;
import br.ifsul.bdii.domain.repository.LivroRepository;
import br.ifsul.bdii.domain.entity.Livro;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService{
    
    private final LivroRepository repository;

    @Override
    public Livro findById(Long id) {
        return repository.findById(
            id
        ).orElseThrow(() -> new ServiceRuleException("Id de livro não encontrado."));
    }

    @Override
    public List<Livro> findByTituloLike(String name) {
        return repository.findByNameLike(name);
    }

    @Override
    public List<Livro> findByDescLike(String desc) {
        return repository.findByDescLike(desc);
    }

    @Override
    public List<Livro> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Livro save(Livro livro) {
        return repository.save(livro);
    }

    @Override
    @Transactional
    public void update(Livro livro, Long id) {
        
        repository.findById(id)
            .map(l -> {
                livro.setId(id);
                repository.save(livro);
                return l;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de livro não encontrado."));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(l -> {
                repository.delete(l);
                return l;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de livro não encontrado."));
    }

}
