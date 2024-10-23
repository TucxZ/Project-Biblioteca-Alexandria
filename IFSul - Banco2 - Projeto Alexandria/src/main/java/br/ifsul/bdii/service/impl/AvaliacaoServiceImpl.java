package br.ifsul.bdii.service.impl;

import br.ifsul.bdii.service.AvaliacaoService;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.ifsul.bdii.exception.ServiceRuleException;
import br.ifsul.bdii.domain.repository.AvaliacaoRepository;
import br.ifsul.bdii.domain.entity.Avaliacao;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService{
    
    private final AvaliacaoRepository repository;

    @Override
    public Avaliacao findById(Long id) {
        return repository.findById(
            id
        ).orElseThrow(() -> new ServiceRuleException("Id de Avaliacao não encontrado."));
    }

    @Override
    public List<Avaliacao> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Avaliacao> findByLivroId(Long id) {
        return repository.findByLivroId(id);
    }

    @Override
    @Transactional
    public Avaliacao save(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    @Override
    @Transactional
    public void update(Avaliacao avaliacao, Long id) {
        
        repository.findById(id)
            .map(a -> {
                avaliacao.setId(id);
                repository.save(avaliacao);
                return a;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de avaliação não encontrado."));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(a -> {
                repository.delete(a);
                return a;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de avaliação não encontrado."));
    }

}
