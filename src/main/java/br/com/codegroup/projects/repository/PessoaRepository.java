package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
