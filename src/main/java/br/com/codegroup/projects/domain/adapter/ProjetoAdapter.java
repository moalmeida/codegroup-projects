package br.com.codegroup.projects.domain.adapter;

import br.com.codegroup.projects.domain.dto.ProjetoResponse;
import br.com.codegroup.projects.entity.Projeto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class ProjetoAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final PessoaAdapter pessoaAdapter = new PessoaAdapter();

    private boolean isPermitidoRemover(Projeto projeto) {
        return Stream.of("iniciado", "em andamento", "encerrado").noneMatch(s -> s.equalsIgnoreCase(projeto.getStatus()));
    }

    public ProjetoResponse transformarProjetoResponse(Projeto projeto) {
        return ProjetoResponse.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio() != null ? projeto.getDataInicio().format(formatter) : null)
                .dataPrevisaoFim(projeto.getDataPrevisaoFim() != null ? projeto.getDataPrevisaoFim().format(formatter) : null)
                .dataFim(projeto.getDataFim() != null ? projeto.getDataFim().format(formatter) : null)
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento() != null ? projeto.getOrcamento().longValue() : 0)
                .risco(projeto.getRisco())
                .gerente(projeto.getGerente() != null ? pessoaAdapter.transformarPessoaResponse(projeto.getGerente()) : null)
                .funcionarios(pessoaAdapter.transformarPessoasResponse(projeto.getFuncionarios()))
                .permitidoRemover(isPermitidoRemover(projeto))
                .build();
    }

    public List<ProjetoResponse> transformarProjetosResponse(List<Projeto> projetos) {
        return projetos.stream().map(this::transformarProjetoResponse).toList();
    }

}
