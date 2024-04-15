package br.com.codegroup.projects.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjetoRequest {
    private Long id;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String dataPrevisaoFim;
    private String status;
    private Long gerente;
    private String funcionario;
    private BigDecimal orcamento;
    private String risco;
    private String funcionarios;
}
