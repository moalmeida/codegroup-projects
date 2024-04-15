package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setUp() {
        pessoaRepository.deleteAll();
        pessoaRepository.flush();
    }


    @Test
    void buscarTodosGerentes() {

        Pessoa gerente = new Pessoa();
        gerente.setNome("#gerente");
        gerente.setGerente(true);
        pessoaRepository.save(gerente);
        pessoaRepository.flush();

        List<Pessoa> pessoas = pessoaRepository.buscarTodosGerentes();

        assertEquals(1, pessoas.size());
        assertEquals(true, pessoas.get(0).getGerente());
    }

    @Test
    void buscarTodosFuncionarios() {

        Pessoa funcionario = new Pessoa();
        funcionario.setNome("#funcionario");
        funcionario.setFuncionario(true);
        pessoaRepository.save(funcionario);
        pessoaRepository.flush();

        List<Pessoa> pessoas = pessoaRepository.buscarTodosFuncionarios();

        assertEquals(1, pessoas.size());
        assertEquals(true, pessoas.get(0).getFuncionario());
    }


    @Test
    void buscarPorId() {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa1");
        pessoa.setGerente(true);
        pessoaRepository.save(pessoa);
        pessoaRepository.flush();

        Optional<Pessoa> buscarPessoa = pessoaRepository.buscarPorId(pessoa.getId());

        assertTrue(buscarPessoa.isPresent());
        assertEquals("#pessoa1", buscarPessoa.get().getNome());
    }


    @Test
    void buscarTodos() {

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

        List<Pessoa> pessoas = pessoaRepository.findAll();

        assertEquals(2, pessoas.size());
    }


    @Test
    void salvar() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");
        pessoa.setFuncionario(true);
        pessoa.setGerente(false);

        pessoaRepository.salvar(pessoa);
        pessoaRepository.flush();

        List<Pessoa> pessoas = pessoaRepository.findAll();

        assertEquals(1, pessoas.size());
    }

    @Test
    void removerPorId() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa1");
        pessoa.setGerente(true);
        pessoaRepository.save(pessoa);
        pessoaRepository.flush();

        pessoaRepository.removerPorId(pessoa.getId());
        pessoaRepository.flush();

        List<Pessoa> pessoas = pessoaRepository.findAll();

        assertEquals(0, pessoas.size());
    }

}