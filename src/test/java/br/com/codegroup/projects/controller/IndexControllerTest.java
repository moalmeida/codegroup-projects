package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    @InjectMocks
    IndexController indexController;

    @Mock
    ProjetoRepository projetoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    Model model;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndex() {
        String viewName = indexController.index();
        assertEquals("index", viewName);
    }

    @Test
    public void testListarProjetos() {
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();
        when(projetoRepository.findAll()).thenReturn(Arrays.asList(projeto1, projeto2));

        String viewName = indexController.listarProjetos(model);

        assertEquals("projetos/list", viewName);
        verify(projetoRepository, times(1)).findAll();
    }

    @Test
    public void testIncluirProjeto() {
        Projeto projeto = new Projeto();
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        String viewName = indexController.incluirProjeto(model, projeto);

        assertEquals("redirect:/projetos", viewName);
        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    public void testRemoverProjeto() {
        Long id = 1L;
        doNothing().when(projetoRepository).deleteById(id);

        String viewName = indexController.removerProjeto(id);

        assertEquals("redirect:/projetos", viewName);
        verify(projetoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testCriarProjeto() {
        List<Pessoa> gerentes = new ArrayList<>();
        List<Pessoa> funcionarios = new ArrayList<>();
        when(pessoaRepository.findAllByGerente(true)).thenReturn(gerentes);
        when(pessoaRepository.findAllByFuncionario(true)).thenReturn(funcionarios);

        String viewName = indexController.criarProjeto(model);

        verify(pessoaRepository, times(1)).findAllByGerente(true);
        verify(pessoaRepository, times(1)).findAllByFuncionario(true);
        verify(model, times(1)).addAttribute("gerentes", gerentes);
        verify(model, times(1)).addAttribute("funcionarios", funcionarios);
        assertEquals("projetos/form", viewName);
    }

    @Test
    public void testAlterarProjeto() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));

        String viewName = indexController.alterarProjeto(model, id);

        assertEquals("projetos/form", viewName);
        verify(projetoRepository, times(1)).findById(id);
    }


}