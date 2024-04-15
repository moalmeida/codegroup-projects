package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Membros;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembrosRepository extends JpaRepository<Membros, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Membros m WHERE m.projeto.id = ?1")
    void deletarTodosPorProjeto(Long idProjeto);

    @Transactional
    default void salvarTodos(List<Membros> membros) {
        this.saveAll(membros);
    }

}
