package br.com.codegroup.projects.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PessoaResponse {
    private Long id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private Boolean gerente;
    private Boolean funcionario;
}
