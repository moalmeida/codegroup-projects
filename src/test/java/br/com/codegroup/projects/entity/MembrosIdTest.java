package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MembrosIdTest {

    @Test
    public void testMembrosId() {
        Long idProjeto = 1L;
        Long idPessoa = 1L;
        MembrosId membrosId = new MembrosId(idProjeto, idPessoa);

        assertEquals(idProjeto, membrosId.getIdProjeto());
        assertEquals(idPessoa, membrosId.getIdPessoa());
    }

    @Test
    public void testSetters() {
        MembrosId membrosId = new MembrosId();

        Long idProjeto = 1L;
        membrosId.setIdProjeto(idProjeto);
        assertEquals(idProjeto, membrosId.getIdProjeto());

        Long idPessoa = 1L;
        membrosId.setIdPessoa(idPessoa);
        assertEquals(idPessoa, membrosId.getIdPessoa());
    }

    @Test
    public void testToString() {
        MembrosId membrosId = new MembrosId(1L, 1L);
        String expected = "MembrosId(idProjeto=1, idPessoa=1)";
        assertEquals(expected, membrosId.toString());
    }

}