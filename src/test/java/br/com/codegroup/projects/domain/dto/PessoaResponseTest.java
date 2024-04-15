package br.com.codegroup.projects.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaResponseTest {

    @Test
    void testSetters() {
        PessoaResponse pessoaResponse = new PessoaResponse();

        pessoaResponse.setId(1L);
        pessoaResponse.setNome("Test Name");
        pessoaResponse.setDataNascimento("01/01/1980");
        pessoaResponse.setCpf("123.456.789-00");
        pessoaResponse.setGerente(true);
        pessoaResponse.setFuncionario(true);

        assertEquals(1L, pessoaResponse.getId());
        assertEquals("Test Name", pessoaResponse.getNome());
        assertEquals("01/01/1980", pessoaResponse.getDataNascimento());
        assertEquals("123.456.789-00", pessoaResponse.getCpf());
        assertEquals(true, pessoaResponse.getGerente());
        assertEquals(true, pessoaResponse.getFuncionario());
    }

}