package br.ifsul.bdii.service.impl;

import br.ifsul.bdii.service.AutorService;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.ifsul.bdii.exception.ServiceRuleException;
import br.ifsul.bdii.domain.repository.AutorRepository;
import br.ifsul.bdii.domain.entity.Autor;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService{
    
    private final AutorRepository repository;

    @Override
    public Autor findById(Long id) {
        return repository.findById(
            id
        ).orElseThrow(() -> new ServiceRuleException("Id de autor não encontrado."));
    }

    @Override
    public List<Autor> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    @Override
    public List<Autor> findByDateBirth(Date dateBirth) {
        return repository.findByDateBirth(dateBirth);
    }

    @Override
    public List<Autor> findByDateDeath(Date dateDeath) {
        return repository.findByDateDeath(dateDeath);
    }

    @Override
    public List<Autor> findByNat(String nation) {
        return repository.findByNat(nation);
    }

    @Override
    public List<Autor> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Autor save(Autor autor) {
        return repository.save(autor);
    }

    @Override
    @Transactional
    public void update(Autor autor, Long id) {
        
        repository.findById(id)
            .map(a -> {
                autor.setId(id);
                repository.save(autor);
                return a;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de autor não encontrado."));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(a -> {
                repository.delete(a);
                return a;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de autor não encontrado."));
    }

}
