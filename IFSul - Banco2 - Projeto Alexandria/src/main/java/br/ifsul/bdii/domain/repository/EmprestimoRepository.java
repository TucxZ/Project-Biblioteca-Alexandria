package br.ifsul.bdii.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import br.ifsul.bdii.domain.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository
<Emprestimo, Long>{

    @Query(" select e from Emprestimo e where e.dataEmprestimo=:dateLoan")
    List<Emprestimo> findByDateLoan(@Param("dateLoan")Date dateLoan);

    @Query(" select e from Emprestimo e where e.dataDevolucao=:dateReturn")
    List<Emprestimo> findByDateReturn(@Param("dateReturn")Date dateReturn);

    @Query(" select e from Emprestimo e where e.dataDevolucao<:dateSince order by dataEmprestimo asc")
    List<Emprestimo> findByDateSince(@Param("dateSince")Date dateSince);

    @Query(" select e from Emprestimo e join e.livro l where l.titulo like:name")
    List<Emprestimo> findByLivroName(@Param("name") String nome);

    @Query(" select e from Emprestimo e join e.usuario u where u.id=:id")
    Optional<Emprestimo> findByUsuarioId(@Param("id") Long id);

    @Query(" select e from Emprestimo e join e.livro l where l.id=:id") 
    Optional<Emprestimo> findByLivroId(@Param("id") Long id);

}