package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setUp() {
        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        Pessoa funcionario = new Pessoa();
        funcionario.setNome("#funcionario");
        funcionario.setFuncionario(true);
        pessoaRepository.save(funcionario);
        pessoaRepository.flush();
    }

    @AfterEach
    void tearDown() {
        pessoaRepository.deleteAll();
        pessoaRepository.flush();
    }

    @Test
    public void testFindAllByGerente() {
        List<Pessoa> pessoas = pessoaRepository.findAllByGerente(true);

        assertEquals(1, pessoas.size());
        assertEquals(true, pessoas.get(0).getGerente());
    }

    @Test
    public void testFindAllByFuncionario() {
        List<Pessoa> pessoas = pessoaRepository.findAllByFuncionario(true);

        assertEquals(1, pessoas.size());
        assertEquals(true, pessoas.get(0).getFuncionario());
    }

}