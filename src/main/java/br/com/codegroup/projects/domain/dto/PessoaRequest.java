package br.com.codegroup.projects.domain.dto;

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
    private String nome;
    private String dataNascimento;
    private String cpf;
    private Boolean gerente;
    private Boolean funcionario;
}
