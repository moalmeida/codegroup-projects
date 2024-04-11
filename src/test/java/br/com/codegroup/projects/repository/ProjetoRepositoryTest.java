package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setUp() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        projetoRepository.flush();

        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);
        projetoRepository.save(projeto);
        projetoRepository.flush();
    }

    @AfterEach
    void tearDown() {
        projetoRepository.deleteAll();
        projetoRepository.flush();
        pessoaRepository.deleteAll();
        pessoaRepository.flush();
    }


    @Test
    public void testFindById() {
        Optional<Projeto> projeto = projetoRepository.findById(1L);

        assertTrue(projeto.isPresent());
        assertEquals("#projeto", projeto.get().getNome());
    }


}