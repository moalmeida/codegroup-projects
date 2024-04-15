package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Projeto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p WHERE p.id = ?1")
    Optional<Projeto> buscarPorId(Long id);

    @Query("SELECT p FROM Projeto p order by p.id")
    List<Projeto> buscarTodos();

    @Modifying
    @Transactional
    @Query("DELETE FROM Projeto p WHERE p.id = ?1")
    void removerPorId(Long id);

    @Transactional
    default Projeto salvar(Projeto projeto) {
        return this.save(projeto);
    }

    default List<String> buscarStatus() {
        return List.of("em análise", "análise realizada", "análise aprovada", "iniciado", "planejado", "em andamento", "encerrado", "cancelado");
    }

    default List<String> buscarRiscos() {
        return List.of("baixo risco", "médio risco", "alto risco");
    }

}
