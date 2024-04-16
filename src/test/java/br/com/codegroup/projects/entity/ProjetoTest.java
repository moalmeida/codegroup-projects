package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjetoTest {

    @Test
    void testProjeto() {
        Long id = 1L;
        String nome = "#projeto";
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataPrevisaoFim = LocalDate.now().plusMonths(1);
        LocalDate dataFim = LocalDate.now();
        String descricao = "Test Description";
        String status = "In Progress";
        double orcamento = 1000L;
        String risco = "Medium";

        Pessoa gerente = new Pessoa(1L, "#gerente", LocalDate.now(), "123.456.789-00", true, true);

        Projeto projeto = new Projeto(1L, nome, dataInicio, dataPrevisaoFim, dataFim, descricao, status, orcamento, risco, gerente, new ArrayList<>());

        assertEquals(id, projeto.getId());
        assertEquals(nome, projeto.getNome());
        assertEquals(dataInicio, projeto.getDataInicio());
        assertEquals(dataPrevisaoFim, projeto.getDataPrevisaoFim());
        assertEquals(dataFim, projeto.getDataFim());
        assertEquals(descricao, projeto.getDescricao());
        assertEquals(status, projeto.getStatus());
        assertEquals(orcamento, projeto.getOrcamento());
        assertEquals(risco, projeto.getRisco());
        assertEquals(gerente, projeto.getGerente());
    }


    @Test
    void testSetters() {
        Projeto projeto = new Projeto();

        Long id = 1L;
        projeto.setId(id);
        assertEquals(id, projeto.getId());

        String nome = "Test Project";
        projeto.setNome(nome);
        assertEquals(nome, projeto.getNome());

        LocalDate dataInicio = LocalDate.now();
        projeto.setDataInicio(dataInicio);
        assertEquals(dataInicio, projeto.getDataInicio());

        LocalDate dataPrevisaoFim = LocalDate.now().plusMonths(1);
        projeto.setDataPrevisaoFim(dataPrevisaoFim);
        assertEquals(dataPrevisaoFim, projeto.getDataPrevisaoFim());

        LocalDate dataFim = LocalDate.now();
        projeto.setDataFim(dataFim);
        assertEquals(dataFim, projeto.getDataFim());

        String descricao = "Test Description";
        projeto.setDescricao(descricao);
        assertEquals(descricao, projeto.getDescricao());

        String status = "In Progress";
        projeto.setStatus(status);
        assertEquals(status, projeto.getStatus());

        double orcamento = 1000L;
        projeto.setOrcamento(orcamento);
        assertEquals(orcamento, projeto.getOrcamento());

        String risco = "Medium";
        projeto.setRisco(risco);
        assertEquals(risco, projeto.getRisco());

        Pessoa gerente = new Pessoa(1L, "Test Manager", LocalDate.now(), "123.456.789-00", true, true);
        projeto.setGerente(gerente);
        assertEquals(gerente, projeto.getGerente());

        List<Pessoa> funcionarios = new ArrayList<>();
        projeto.setFuncionarios(funcionarios);
        assertEquals(funcionarios, projeto.getFuncionarios());

    }


}