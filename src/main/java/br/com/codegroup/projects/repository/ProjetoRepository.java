package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
