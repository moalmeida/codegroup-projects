package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.domain.dto.ProjetoRequest;
import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.MembrosRepository;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProjetoControllerTest {

    @InjectMocks
    ProjetoController projetoController;

    @Mock
    ProjetoRepository projetoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    MembrosRepository membrosRepository;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void buscarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");
        projetos.add(projeto);

        when(projetoRepository.buscarTodos()).thenReturn(projetos);

        String viewName = projetoController.buscarTodos(model);

        assertEquals("projetos/list", viewName);
        verify(model, times(1)).addAttribute(eq("projetos"), any());
        verify(projetoRepository, times(1)).buscarTodos();
    }

    @Test
    void removerPorId() {
        Long id = 1L;
        projetoController.removerPorId(id);

        verify(projetoRepository, times(1)).removerPorId(any());
    }

    @Test
    void salvar() {
        ProjetoRequest projeto = new ProjetoRequest();
        projeto.setNome("#projeto");

        projetoController.salvar(projeto);

        verify(projetoRepository, times(1)).salvar(any());
    }

    @Test
    void formulario() {
        String viewName = projetoController.formulario(model);

        assertEquals("projetos/form", viewName);
    }

    @Test
    void formularioAlterar() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setNome("#projeto");

        when(projetoRepository.buscarPorId(eq(id))).thenReturn(Optional.of(projeto));

        String viewName = projetoController.formularioAlterar(model, id);

        assertEquals("projetos/form", viewName);
        verify(model, times(1)).addAttribute(eq("projeto"), any());
        verify(projetoRepository, times(1)).buscarPorId(eq(id));
    }
}