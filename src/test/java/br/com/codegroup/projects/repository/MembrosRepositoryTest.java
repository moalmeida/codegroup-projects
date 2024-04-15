package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Membros;
import br.com.codegroup.projects.entity.MembrosId;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class MembrosRepositoryTest {

    @Autowired
    private MembrosRepository membrosRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @BeforeEach
    public void setUp() {
        membrosRepository.deleteAll();
        membrosRepository.flush();
        projetoRepository.deleteAll();
        projetoRepository.flush();
        pessoaRepository.deleteAll();
        pessoaRepository.flush();
    }

    @Test
    void deletarTodosPorProjeto() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Pessoa funcionario = new Pessoa();
        funcionario.setNome("#funcionario");
        funcionario.setGerente(true);
        pessoaRepository.save(funcionario);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();

        MembrosId membrosId = new MembrosId(projeto.getId(), funcionario.getId());
        Membros membros = new Membros();
        membros.setId(membrosId);
        membros.setProjeto(projeto);
        membros.setPessoa(funcionario);
        membrosRepository.save(membros);
        membrosRepository.flush();

        List<Membros> membrosBeforeDelete = membrosRepository.findAll();
        assertEquals(1, membrosBeforeDelete.size());

        membrosRepository.deletarTodosPorProjeto(projeto.getId());
        membrosRepository.flush();

        List<Membros> membrosAfterDelete = membrosRepository.findAll();
        assertEquals(0, membrosAfterDelete.size());


    }

    @Test
    void salvarTodos() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Pessoa funcionario = new Pessoa();
        funcionario.setNome("#funcionario");
        funcionario.setGerente(true);
        pessoaRepository.save(funcionario);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();

        MembrosId membrosId = new MembrosId(projeto.getId(), funcionario.getId());
        Membros membros = new Membros();
        membros.setId(membrosId);
        membros.setProjeto(projeto);
        membros.setPessoa(funcionario);

        List<Membros> membrosBeforeSave = membrosRepository.findAll();
        assertEquals(0, membrosBeforeSave.size());

        membrosRepository.salvarTodos(List.of(membros));
        membrosRepository.flush();

        List<Membros> membrosAfterSave = membrosRepository.findAll();
        assertEquals(1, membrosAfterSave.size());
    }
}