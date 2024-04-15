package br.com.codegroup.projects.repository;


import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MembrosRepository membrosRepository;

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
    void buscarPorId() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();

        Optional<Projeto> buscarProjeto = projetoRepository.buscarPorId(projeto.getId());

        assertTrue(buscarProjeto.isPresent());
        assertEquals("#projeto", buscarProjeto.get().getNome());
    }

    @Test
    void buscarTodos() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();

        List<Projeto> projetos = projetoRepository.buscarTodos();

        assertFalse(projetos.isEmpty());
        assertEquals(1, projetos.size());
        assertEquals("#projeto", projetos.get(0).getNome());
    }

    @Test
    void removerPorId() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();

        projetoRepository.removerPorId(projeto.getId());

        List<Projeto> projetos = projetoRepository.buscarTodos();

        assertTrue(projetos.isEmpty());
    }

    @Test
    void salvar() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.salvar(projeto);
        projetoRepository.flush();

        List<Projeto> projetos = projetoRepository.buscarTodos();

        assertFalse(projetos.isEmpty());
        assertEquals(1, projetos.size());
        assertEquals("#projeto", projetos.get(0).getNome());
    }

    @Test
    void buscarStatus() {
        List<String> status = projetoRepository.buscarStatus();

        assertFalse(status.isEmpty());
    }

    @Test
    void buscarRiscos() {
        List<String> riscos = projetoRepository.buscarRiscos();

        assertFalse(riscos.isEmpty());
    }
}