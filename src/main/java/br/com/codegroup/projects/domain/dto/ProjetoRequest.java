package br.com.codegroup.projects.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjetoRequest {
    private Long id;
    @NotBlank
    private String nome;
    private String descricao;
    @Pattern(regexp = "^$|^(\\d{2}/\\d{2}/\\d{4})$", message = "date format should be dd/mm/yyyy")
    private String dataInicio;
    @Pattern(regexp = "^$|^(\\d{2}/\\d{2}/\\d{4})$", message = "date format should be dd/mm/yyyy")
    private String dataFim;
    @Pattern(regexp = "^$|^(\\d{2}/\\d{2}/\\d{4})$", message = "date format should be dd/mm/yyyy")
    private String dataPrevisaoFim;
    private String status;
    @NotNull
    private Long gerente;
    private Double orcamento;
    private String risco;
    private String funcionarios;
}
