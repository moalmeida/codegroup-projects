package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MembrosTest {

    @Test
    void testMembros() {
        MembrosId membrosId = new MembrosId(1L, 1L);
        Projeto projeto = new Projeto(1L);
        Pessoa pessoa = new Pessoa(1L);
        Membros membros = new Membros(membrosId, projeto, pessoa);

        assertEquals(projeto, membros.getProjeto());
        assertEquals(pessoa, membros.getPessoa());
    }

}