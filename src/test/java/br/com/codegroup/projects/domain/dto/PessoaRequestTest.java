package br.com.codegroup.projects.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaRequestTest {

    @Test
    void testSetters() {
        PessoaRequest pessoaRequest = new PessoaRequest();

        pessoaRequest.setId(1L);
        pessoaRequest.setNome("Test Name");
        pessoaRequest.setDataNascimento("01/01/1980");
        pessoaRequest.setCpf("123.456.789-00");
        pessoaRequest.setGerente(true);
        pessoaRequest.setFuncionario(true);

        assertEquals(1L, pessoaRequest.getId());
        assertEquals("Test Name", pessoaRequest.getNome());
        assertEquals("01/01/1980", pessoaRequest.getDataNascimento());
        assertEquals("123.456.789-00", pessoaRequest.getCpf());
        assertEquals(true, pessoaRequest.getGerente());
        assertEquals(true, pessoaRequest.getFuncionario());
    }

}