package br.com.codegroup.projects.domain.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjetoResponseTest {

    @Test
    void testSetters() {
        ProjetoResponse projetoResponse = new ProjetoResponse();

        projetoResponse.setId(1L);
        projetoResponse.setNome("Test Project");
        projetoResponse.setDescricao("Test Description");
        projetoResponse.setDataInicio("01/01/2022");
        projetoResponse.setDataFim("01/02/2022");
        projetoResponse.setDataPrevisaoFim("01/03/2022");
        projetoResponse.setStatus("In Progress");
        projetoResponse.setGerente(new PessoaResponse(1L, "Test Manager", "01/01/1980", "123.456.789-00", true, true));
        projetoResponse.setFuncionario("Test Funcionario");
        projetoResponse.setOrcamento(1000L);
        projetoResponse.setRisco("Medium");
        projetoResponse.setFuncionarios(Collections.singletonList(new PessoaResponse(2L, "Test Funcionario", "01/01/1980", "123.456.789-01", false, true)));
        projetoResponse.setPermitidoRemover(true);

        assertEquals(1L, projetoResponse.getId());
        assertEquals("Test Project", projetoResponse.getNome());
        assertEquals("Test Description", projetoResponse.getDescricao());
        assertEquals("01/01/2022", projetoResponse.getDataInicio());
        assertEquals("01/02/2022", projetoResponse.getDataFim());
        assertEquals("01/03/2022", projetoResponse.getDataPrevisaoFim());
        assertEquals("In Progress", projetoResponse.getStatus());
        assertEquals(1L, projetoResponse.getGerente().getId());
        assertEquals("Test Funcionario", projetoResponse.getFuncionario());
        assertEquals(1000L, projetoResponse.getOrcamento());
        assertEquals("Medium", projetoResponse.getRisco());
        assertEquals(1, projetoResponse.getFuncionarios().size());
        assertEquals(2L, projetoResponse.getFuncionarios().get(0).getId());
        assertTrue(projetoResponse.isPermitidoRemover());

    }

}