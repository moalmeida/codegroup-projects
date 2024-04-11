package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Membros;
import br.com.codegroup.projects.repository.MembrosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MembrosControllerTest {

    @InjectMocks
    MembrosController membrosController;

    @Mock
    MembrosRepository membrosRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionarMembros() {
        Membros membros = new Membros();
        when(membrosRepository.save(any(Membros.class))).thenReturn(membros);

        Membros response = membrosController.adicionarMembros(membros);

        assertEquals(membros, response);
        verify(membrosRepository, times(1)).save(any(Membros.class));
    }

}