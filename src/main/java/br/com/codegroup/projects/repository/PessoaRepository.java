package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.gerente = true order by p.nome asc")
    List<Pessoa> buscarTodosGerentes();

    @Query("SELECT p FROM Pessoa p WHERE p.funcionario = true order by p.nome asc")
    List<Pessoa> buscarTodosFuncionarios();

    @Query("SELECT p FROM Pessoa p WHERE p.id = ?1")
    Optional<Pessoa> buscarPorId(Long id);

    @Query("SELECT p FROM Pessoa p order by p.id")
    List<Pessoa> buscarTodos();

    @Modifying
    @Transactional
    @Query("DELETE FROM Pessoa p WHERE p.id = ?1")
    void removerPorId(Long id);

    @Transactional
    default Pessoa salvar(Pessoa pessoa) {
        return this.save(pessoa);
    }

}

