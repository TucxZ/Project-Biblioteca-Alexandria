package br.ifsul.bdii.service;

import java.sql.Date;
import java.util.List;
import br.ifsul.bdii.domain.entity.Emprestimo;

public interface EmprestimoService{

    Emprestimo findById(Long id);
    List<Emprestimo> findByDateLoan(Date dateLoan);
    List<Emprestimo> findByDateReturn(Date dateReturn);
    List<Emprestimo> findByDateSince(Date dateSince);
    List<Emprestimo> findByLivroNome(String name);
    Emprestimo findByLivroId(Long id);
    Emprestimo findByUsuarioId(Long id);
    List<Emprestimo> findAll();
    Emprestimo save (Emprestimo emprestimo);
    void update (Emprestimo emprestimo, Long id);
    void delete (Long id);

}