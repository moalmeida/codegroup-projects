package br.com.codegroup.projects.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetoTest {

    @Test
    public void testProjeto() {
        Long id = 1L;
        String nome = "Test Project";
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataPrevisaoFim = LocalDate.now().plusMonths(1);
        LocalDate dataFim = LocalDate.now();
        String descricao = "Test Description";
        String status = "In Progress";
        Float orcamento = 1000.0f;
        String risco = "Medium";
        Pessoa gerente = new Pessoa(1L, "Test Manager", LocalDate.now(), "123.456.789-00", true, true);

        Projeto projeto = new Projeto(id, nome, dataInicio, dataPrevisaoFim, dataFim, descricao, status, orcamento, risco, gerente, null);

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
    public void testIsPermitidoRemover() {
        Projeto projeto = new Projeto();

        projeto.setStatus("iniciado");
        assertFalse(projeto.isPermitidoRemover());

        projeto.setStatus("em andamento");
        assertFalse(projeto.isPermitidoRemover());

        projeto.setStatus("encerrado");
        assertFalse(projeto.isPermitidoRemover());

        projeto.setStatus("finalizado");
        assertTrue(projeto.isPermitidoRemover());
    }

    @Test
    public void testSetters() {
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

        Float orcamento = 1000.0f;
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

    @Test
    public void testToString() {
        Pessoa gerente = new Pessoa(1L, "Test Manager", LocalDate.now(), "123.456.789-00", true, true);
        Projeto projeto = new Projeto(1L, "Test Project", LocalDate.now(), LocalDate.now().plusMonths(1), LocalDate.now(), "Test Description", "In Progress", 1000.0f, "Medium", gerente, new ArrayList<>());
        String expected = "Projeto(id=1, nome=Test Project, dataInicio=" + LocalDate.now() + ", dataPrevisaoFim=" + LocalDate.now().plusMonths(1) + ", dataFim=" + LocalDate.now() + ", descricao=Test Description, status=In Progress, orcamento=1000.0, risco=Medium, gerente=" + gerente + ", funcionarios=[])";
        assertEquals(expected, projeto.toString());
    }


}