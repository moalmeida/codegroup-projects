package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findAllByGerente(boolean gerente);

    List<Pessoa> findAllByFuncionario(boolean funcionario);

}
