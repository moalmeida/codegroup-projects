package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.domain.dto.PessoaRequest;
import br.com.codegroup.projects.domain.dto.PessoaResponse;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.MembrosRepository;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PessoaControllerTest {

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    ProjetoRepository projetoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    MembrosRepository membrosRepository;

    @Mock
    Model model;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }


    @Test
    void buscarTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");
        pessoas.add(pessoa);

        when(pessoaRepository.buscarTodos()).thenReturn(pessoas);

        ResponseEntity<List<PessoaResponse>> response = pessoaController.buscarTodos();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void buscarPorId() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");

        when(pessoaRepository.buscarPorId(id)).thenReturn(Optional.of(pessoa));

        ResponseEntity<PessoaResponse> response = pessoaController.buscarPorId(id);

        assertNotNull(response.getBody());
        assertEquals("#pessoa", response.getBody().getNome());
    }

    @Test
    void salvar() {
        PessoaRequest request = new PessoaRequest();
        request.setNome("#pessoa");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");

        when(pessoaRepository.salvar(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<PessoaResponse> response = pessoaController.salvar(request);

        assertNotNull(response.getBody());
        assertEquals("#pessoa", response.getBody().getNome());
    }

    @Test
    void atualizar() {
        Long id = 1L;
        PessoaRequest request = new PessoaRequest();
        request.setNome("#pessoa");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");

        when(pessoaRepository.buscarPorId(id)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.salvar(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<PessoaResponse> response = pessoaController.atualizar(id, request);

        assertNotNull(response.getBody());
        assertEquals("#pessoa", response.getBody().getNome());
    }

    @Test
    void atualizarSemObjeto() {
        Long id = 1L;
        PessoaRequest request = new PessoaRequest();
        request.setNome("#pessoa");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");

        when(pessoaRepository.buscarPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<PessoaResponse> response = pessoaController.atualizar(id, request);

        assertEquals(404, response.getStatusCodeValue());
    }


    @Test
    void removerPorId() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");

        when(pessoaRepository.buscarPorId(id)).thenReturn(Optional.of(pessoa));

        var response = pessoaController.removerPorId(id);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void buscarRelatorio() throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("#pessoa");
        pessoas.add(pessoa);

        when(pessoaRepository.buscarTodos()).thenReturn(pessoas);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pessoas/relatorio")
                        .accept("application/pdf"))
                .andReturn().getResponse();

        assertNotNull(response.getContentAsString());
        assertEquals("application/pdf", response.getContentType());
    }

}