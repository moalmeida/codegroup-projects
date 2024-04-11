package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjetoControllerTest {

    @InjectMocks
    ProjetoController projetoController;

    @Mock
    ProjetoRepository projetoRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvar() {
        Projeto projeto = new Projeto();
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        ResponseEntity<Projeto> response = projetoController.salvar(projeto);

        assertEquals(projeto, response.getBody());
        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    public void testAtualizar() {
        Long id = 1L;
        Projeto entrada = new Projeto();
        entrada.setNome("Updated Name");

        Projeto entidade = new Projeto();
        entidade.setNome("Old Name");

        when(projetoRepository.findById(id)).thenReturn(Optional.of(entidade));
        when(projetoRepository.save(any(Projeto.class))).thenReturn(entrada);

        ResponseEntity<Projeto> response = projetoController.atualizar(id, entrada);

        assertEquals(entrada.getNome(), Objects.requireNonNull(response.getBody()).getNome());
        verify(projetoRepository, times(1)).findById(id);
        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    public void testAtualizarWhenEntidadeIsEmpty() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        when(projetoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Projeto> response = projetoController.atualizar(id, projeto);

        verify(projetoRepository, times(1)).findById(id);
    }

}