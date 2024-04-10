package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoRepository projetoRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void salvar() {
    }

    @Test
    void buscarTodos() throws Exception {

        Projeto p1 = new Projeto();
        p1.setId(1L);
        Projeto p2 = new Projeto();
        p2.setId(2L);

        given(projetoRepository.findAll()).willReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/api/projetos"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void buscarStatus() {
    }

    @Test
    void buscarRiscos() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void remover() {
    }
}