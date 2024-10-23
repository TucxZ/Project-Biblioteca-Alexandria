package br.ifsul.bdii.service.impl;

import br.ifsul.bdii.service.EmprestimoService;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.ifsul.bdii.exception.ServiceRuleException;
import br.ifsul.bdii.domain.repository.EmprestimoRepository;
import br.ifsul.bdii.domain.entity.Emprestimo;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService{
    
    private final EmprestimoRepository repository;

    @Override
    public Emprestimo findById(Long id) {
        return repository.findById(
            id
        ).orElseThrow(() -> new ServiceRuleException("Id de empréstimo não encontrado."));
    }

    @Override
    public List<Emprestimo> findByDateLoan(Date dateLoan) {
        return repository.findByDateLoan(dateLoan);
    }

    @Override
    public List<Emprestimo> findByDateReturn(Date dateReturn) {
        return repository.findByDateReturn(dateReturn);
    }

    @Override
    public List<Emprestimo> findByDateSince(Date dateSince) {
        return repository.findByDateSince(dateSince);
    }
    
    @Override
    public List<Emprestimo> findByLivroNome(String name) {
        return repository.findByLivroName(name);
    }

    @Override
    public Emprestimo findByLivroId(Long id) {
        return repository.findByLivroId(
            id
        ).orElse(null);
    }

    @Override
    public Emprestimo findByUsuarioId(Long id) {
        return repository.findByUsuarioId(
            id
            ).orElse(null);
    }

    @Override
    public List<Emprestimo> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Emprestimo save(Emprestimo emprestimo) {
        return repository.save(emprestimo);
    }

    @Override
    @Transactional
    public void update(Emprestimo emprestimo, Long id) {
        
        repository.findById(id)
            .map(e -> {
                emprestimo.setId(id);
                repository.save(emprestimo);
                return e;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de empréstimo não encontrado."));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
            .map(e -> {
                repository.delete(e);
                return e;
            }).orElseThrow(
                () -> new ServiceRuleException("Id de empréstimo não encontrado."));
    }

}
