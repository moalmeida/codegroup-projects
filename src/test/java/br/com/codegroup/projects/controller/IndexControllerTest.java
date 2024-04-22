package br.com.codegroup.projects.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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