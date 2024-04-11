package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PessoaControllerTest {

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    PessoaRepository pessoaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodos() {
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));

        ResponseEntity<List<Pessoa>> response = pessoaController.buscarTodos();

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        ResponseEntity<Pessoa> response = pessoaController.buscarPorId(1L);

        assertEquals(pessoa, response.getBody());
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    public void testSalvar() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa response = pessoaController.salvar(pessoa);

        assertEquals(pessoa, response);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    public void testAtualizar() {
        Pessoa entrada = new Pessoa();
        entrada.setNome("Updated Name");

        Pessoa entidade = new Pessoa();
        entidade.setNome("Old Name");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(entidade));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(entrada);

        ResponseEntity<Pessoa> response = pessoaController.atualizar(1L, entrada);

        assertEquals(entrada.getNome(), Objects.requireNonNull(response.getBody()).getNome());
        verify(pessoaRepository, times(1)).findById(1L);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    public void testAtualizarWhenEntidadeIsEmpty() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Pessoa> response = pessoaController.atualizar(id, pessoa);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testRemover() {
        Long id = 1L;

        doNothing().when(pessoaRepository).deleteById(id);
        pessoaController.remover(id);

        verify(pessoaRepository, times(1)).deleteById(id);
    }

}