package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.domain.dto.ProjetoRequest;
import br.com.codegroup.projects.entity.Pessoa;
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

        Pessoa gerente = new Pessoa();
        gerente.setId(1L);
        gerente.setNome("#gerente");

        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("#projeto");
        projeto.setGerente(gerente);

        ProjetoRequest request = new ProjetoRequest();
        request.setId(1L);
        request.setNome("#projeto");
        request.setDataFim("31/12/2021");
        request.setDataInicio("01/01/2021");
        request.setDataPrevisaoFim("30/12/2021");
        request.setFuncionarios("2");
        request.setGerente(1L);

        when(pessoaRepository.buscarPorId(1L)).thenReturn(Optional.of(gerente));

        doNothing().when(membrosRepository).deletarTodosPorProjeto(any());
        doNothing().when(membrosRepository).salvarTodos(any());

        projetoController.salvar(request);

        verify(projetoRepository, times(1)).salvar(any());
    }

    @Test
    void salvarComCamposVazios() {

        Pessoa gerente = new Pessoa();
        gerente.setId(1L);
        gerente.setNome("#gerente");

        ProjetoRequest request = new ProjetoRequest();
        request.setId(1L);
        request.setNome("#projeto");
        request.setGerente(1L);

        when(pessoaRepository.buscarPorId(1L)).thenReturn(Optional.of(gerente));

        doNothing().when(membrosRepository).deletarTodosPorProjeto(any());
        doNothing().when(membrosRepository).salvarTodos(any());

        projetoController.salvar(request);

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