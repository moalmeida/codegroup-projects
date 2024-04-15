package br.com.codegroup.projects.domain.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjetoRequestTest {

    @Test
    void testSetters() {
        ProjetoRequest projetoRequest = new ProjetoRequest();

        projetoRequest.setId(1L);
        projetoRequest.setNome("Test Project");
        projetoRequest.setDescricao("Test Description");
        projetoRequest.setDataInicio("01/01/2022");
        projetoRequest.setDataFim("01/02/2022");
        projetoRequest.setDataPrevisaoFim("01/03/2022");
        projetoRequest.setStatus("In Progress");
        projetoRequest.setGerente(1L);
        projetoRequest.setFuncionario("Test Funcionario");
        projetoRequest.setOrcamento(new BigDecimal(1000.0));
        projetoRequest.setRisco("Medium");
        projetoRequest.setFuncionarios("Test Funcionarios");

        assertEquals(1L, projetoRequest.getId());
        assertEquals("Test Project", projetoRequest.getNome());
        assertEquals("Test Description", projetoRequest.getDescricao());
        assertEquals("01/01/2022", projetoRequest.getDataInicio());
        assertEquals("01/02/2022", projetoRequest.getDataFim());
        assertEquals("01/03/2022", projetoRequest.getDataPrevisaoFim());
        assertEquals("In Progress", projetoRequest.getStatus());
        assertEquals(1L, projetoRequest.getGerente());
        assertEquals("Test Funcionario", projetoRequest.getFuncionario());
        assertEquals(new BigDecimal(1000.0), projetoRequest.getOrcamento());
        assertEquals("Medium", projetoRequest.getRisco());
        assertEquals("Test Funcionarios", projetoRequest.getFuncionarios());
    }

}