package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MembrosIdTest {

    @Test
    void testMembrosId() {
        Long idProjeto = 1L;
        Long idPessoa = 1L;
        MembrosId membrosId = new MembrosId(idProjeto, idPessoa);

        assertEquals(idProjeto, membrosId.getIdprojeto());
        assertEquals(idPessoa, membrosId.getIdpessoa());
    }

    @Test
    void testSetters() {
        MembrosId membrosId = new MembrosId();

        Long idProjeto = 1L;
        membrosId.setIdprojeto(idProjeto);
        assertEquals(idProjeto, membrosId.getIdprojeto());

        Long idPessoa = 1L;
        membrosId.setIdpessoa(idPessoa);
        assertEquals(idPessoa, membrosId.getIdpessoa());
    }


}