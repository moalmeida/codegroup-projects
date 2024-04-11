package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PessoaTest {

    @Test
    public void testPessoa() {
        Long id = 1L;
        String nome = "Test Name";
        LocalDate dataNascimento = LocalDate.now();
        String cpf = "123.456.789-00";
        Boolean funcionario = true;
        Boolean gerente = false;

        Pessoa pessoa = new Pessoa(id, nome, dataNascimento, cpf, funcionario, gerente);

        assertEquals(id, pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals(cpf, pessoa.getCpf());
        assertEquals(funcionario, pessoa.getFuncionario());
        assertEquals(gerente, pessoa.getGerente());
    }


    @Test
    public void testSetters() {
        Pessoa pessoa = new Pessoa();

        Long id = 1L;
        pessoa.setId(id);
        assertEquals(id, pessoa.getId());

        String nome = "Test Name";
        pessoa.setNome(nome);
        assertEquals(nome, pessoa.getNome());

        LocalDate dataNascimento = LocalDate.now();
        pessoa.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento, pessoa.getDataNascimento());

        String cpf = "123.456.789-00";
        pessoa.setCpf(cpf);
        assertEquals(cpf, pessoa.getCpf());

        Boolean funcionario = true;
        pessoa.setFuncionario(funcionario);
        assertEquals(funcionario, pessoa.getFuncionario());

        Boolean gerente = false;
        pessoa.setGerente(gerente);
        assertEquals(gerente, pessoa.getGerente());
    }

    @Test
    public void testToString() {
        Pessoa pessoa = new Pessoa(1L, "Test Name", LocalDate.now(), "123.456.789-00", true, true);
        String expected = "Pessoa(id=1, nome=Test Name, dataNascimento=" + LocalDate.now() + ", cpf=123.456.789-00, funcionario=true, gerente=true)";
        assertEquals(expected, pessoa.toString());
    }


}