package br.com.codegroup.projects.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PessoaRequest {
    private Long id;
    @NotBlank
    private String nome;
    @Pattern(regexp = "^$|^(\\d{2}/\\d{2}/\\d{4})$", message = "date format should be dd/mm/yyyy")
    private String dataNascimento;
    private String cpf;
    private Boolean gerente;
    private Boolean funcionario;
}
