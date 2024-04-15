package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndexControllerTest {

    @InjectMocks
    IndexController indexController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void index() {
        String viewName = indexController.index();
        assertEquals("index", viewName);
    }

}