package br.com.codegroup.projects.repository;


import br.com.codegroup.projects.entity.Membros;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
class MembrosRepositoryTest {

    @Autowired
    private MembrosRepository membrosRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;


    @BeforeEach
    public void setUp() {

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

        Membros membros = new Membros();
        membros.setIdProjeto(projeto.getId());
        membros.setIdPessoa(funcionario.getId());
        membrosRepository.save(membros);

        membrosRepository.flush();
    }

    @AfterEach
    void tearDown() {
        membrosRepository.deleteAll();
        membrosRepository.flush();
        projetoRepository.deleteAll();
        projetoRepository.flush();
        pessoaRepository.deleteAll();
        pessoaRepository.flush();
    }


}