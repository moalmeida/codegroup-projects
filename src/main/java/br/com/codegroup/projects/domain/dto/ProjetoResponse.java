package br.com.codegroup.projects.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjetoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String dataPrevisaoFim;
    private String status;
    private PessoaResponse gerente;
    private String funcionario;
    private long orcamento;
    private String risco;
    private List<PessoaResponse> funcionarios;
    private boolean permitidoRemover;
}
