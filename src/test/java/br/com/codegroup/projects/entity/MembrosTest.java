package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembrosTest {

    @Test
    public void testMembros() {
        Long idProjeto = 1L;
        Long idPessoa = 1L;
        Membros membros = new Membros(idProjeto, idPessoa);

        assertEquals(idProjeto, membros.getIdProjeto());
        assertEquals(idPessoa, membros.getIdPessoa());
    }

    @Test
    public void testSetters() {
        Membros membros = new Membros();

        Long idProjeto = 1L;
        membros.setIdProjeto(idProjeto);
        assertEquals(idProjeto, membros.getIdProjeto());

        Long idPessoa = 1L;
        membros.setIdPessoa(idPessoa);
        assertEquals(idPessoa, membros.getIdPessoa());
    }

    @Test
    public void testToString() {
        Membros membros = new Membros(1L, 1L);
        String expected = "Membros(idProjeto=1, idPessoa=1)";
        assertEquals(expected, membros.toString());
    }


}