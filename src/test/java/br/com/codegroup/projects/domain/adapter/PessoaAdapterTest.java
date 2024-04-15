package br.com.codegroup.projects.domain.adapter;

import br.com.codegroup.projects.domain.dto.PessoaResponse;
import br.com.codegroup.projects.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaAdapterTest {

    PessoaAdapter pessoaAdapter = new PessoaAdapter();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void transformarPessoaResponse() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Test Name");
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setCpf("123.456.789-00");
        pessoa.setFuncionario(true);
        pessoa.setGerente(false);

        PessoaResponse pessoaResponse = pessoaAdapter.transformarPessoaResponse(pessoa);

        assertEquals(pessoa.getId(), pessoaResponse.getId());
        assertEquals(pessoa.getNome(), pessoaResponse.getNome());
        assertEquals(pessoa.getDataNascimento().format(formatter), pessoaResponse.getDataNascimento());
        assertEquals(pessoa.getCpf(), pessoaResponse.getCpf());
        assertEquals(pessoa.getFuncionario(), pessoaResponse.getFuncionario());
        assertEquals(pessoa.getGerente(), pessoaResponse.getGerente());
    }

    @Test
    void transformarPessoasResponse() {
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Test Name 1");
        pessoa1.setDataNascimento(LocalDate.now());
        pessoa1.setCpf("123.456.789-01");
        pessoa1.setFuncionario(true);
        pessoa1.setGerente(false);
        pessoas.add(pessoa1);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Test Name 2");
        pessoa2.setDataNascimento(LocalDate.now());
        pessoa2.setCpf("123.456.789-02");
        pessoa2.setFuncionario(false);
        pessoa2.setGerente(true);
        pessoas.add(pessoa2);

        List<PessoaResponse> pessoasResponse = pessoaAdapter.transformarPessoasResponse(pessoas);

        assertEquals(pessoas.size(), pessoasResponse.size());

        for (int i = 0; i < pessoas.size(); i++) {
            assertEquals(pessoas.get(i).getId(), pessoasResponse.get(i).getId());
            assertEquals(pessoas.get(i).getNome(), pessoasResponse.get(i).getNome());
            assertEquals(pessoas.get(i).getDataNascimento().format(formatter), pessoasResponse.get(i).getDataNascimento());
            assertEquals(pessoas.get(i).getCpf(), pessoasResponse.get(i).getCpf());
            assertEquals(pessoas.get(i).getFuncionario(), pessoasResponse.get(i).getFuncionario());
            assertEquals(pessoas.get(i).getGerente(), pessoasResponse.get(i).getGerente());
        }
    }
}