package br.com.codegroup.projects.domain.adapter;

import br.com.codegroup.projects.domain.dto.ProjetoResponse;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjetoAdapterTest {

    ProjetoAdapter projetoAdapter = new ProjetoAdapter();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void transformarProjetoResponse() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Test Project");
        projeto.setDataInicio(LocalDate.now());
        projeto.setDataPrevisaoFim(LocalDate.now().plusMonths(1));
        projeto.setDataFim(LocalDate.now());
        projeto.setDescricao("Test Description");
        projeto.setStatus("In Progress");
        projeto.setOrcamento(1000D);
        projeto.setRisco("Medium");

        Pessoa gerente = new Pessoa(1L, "Test Manager", LocalDate.now(), "123.456.789-00", true, true);
        projeto.setGerente(gerente);

        ProjetoResponse projetoResponse = projetoAdapter.transformarProjetoResponse(projeto);

        assertEquals(projeto.getId(), projetoResponse.getId());
        assertEquals(projeto.getNome(), projetoResponse.getNome());
        assertEquals(projeto.getDataInicio().format(formatter), projetoResponse.getDataInicio());
        assertEquals(projeto.getDataPrevisaoFim().format(formatter), projetoResponse.getDataPrevisaoFim());
        assertEquals(projeto.getDataFim().format(formatter), projetoResponse.getDataFim());
        assertEquals(projeto.getDescricao(), projetoResponse.getDescricao());
        assertEquals(projeto.getStatus(), projetoResponse.getStatus());
        assertEquals(projeto.getOrcamento(), projetoResponse.getOrcamento());
        assertEquals(projeto.getRisco(), projetoResponse.getRisco());
        assertEquals(projeto.getGerente().getId(), projetoResponse.getGerente().getId());
    }

    @Test
    void transformarProjetosResponse() {
        List<Projeto> projetos = new ArrayList<>();
        Projeto projeto1 = new Projeto();
        projeto1.setId(1L);
        projeto1.setNome("Test Project 1");
        projeto1.setDataInicio(LocalDate.now());
        projeto1.setDataPrevisaoFim(LocalDate.now().plusMonths(1));
        projeto1.setDataFim(LocalDate.now());
        projeto1.setDescricao("Test Description 1");
        projeto1.setStatus("In Progress");
        projeto1.setOrcamento(1000D);
        projeto1.setRisco("Medium");
        Pessoa gerente1 = new Pessoa(1L, "Test Manager 1", LocalDate.now(), "123.456.789-01", true, true);
        projeto1.setGerente(gerente1);
        projetos.add(projeto1);

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setNome("Test Project 2");
        projeto2.setDataInicio(LocalDate.now());
        projeto2.setDataPrevisaoFim(LocalDate.now().plusMonths(1));
        projeto2.setDataFim(LocalDate.now());
        projeto2.setDescricao("Test Description 2");
        projeto2.setStatus("Completed");
        projeto2.setOrcamento(2000D);
        projeto2.setRisco("Low");
        Pessoa gerente2 = new Pessoa(2L, "Test Manager 2", LocalDate.now(), "123.456.789-02", true, true);
        projeto2.setGerente(gerente2);
        projetos.add(projeto2);

        List<ProjetoResponse> projetosResponse = projetoAdapter.transformarProjetosResponse(projetos);

        assertEquals(projetos.size(), projetosResponse.size());

        for (int i = 0; i < projetos.size(); i++) {
            assertEquals(projetos.get(i).getId(), projetosResponse.get(i).getId());
            assertEquals(projetos.get(i).getNome(), projetosResponse.get(i).getNome());
            assertEquals(projetos.get(i).getDataInicio().format(formatter), projetosResponse.get(i).getDataInicio());
            assertEquals(projetos.get(i).getDataPrevisaoFim().format(formatter), projetosResponse.get(i).getDataPrevisaoFim());
            assertEquals(projetos.get(i).getDataFim().format(formatter), projetosResponse.get(i).getDataFim());
            assertEquals(projetos.get(i).getDescricao(), projetosResponse.get(i).getDescricao());
            assertEquals(projetos.get(i).getStatus(), projetosResponse.get(i).getStatus());
            assertEquals(projetos.get(i).getOrcamento(), projetosResponse.get(i).getOrcamento());
            assertEquals(projetos.get(i).getRisco(), projetosResponse.get(i).getRisco());
            assertEquals(projetos.get(i).getGerente().getId(), projetosResponse.get(i).getGerente().getId());
        }
    }
}