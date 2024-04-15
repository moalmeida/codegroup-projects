package br.com.codegroup.projects.domain.adapter;

import br.com.codegroup.projects.domain.dto.PessoaResponse;
import br.com.codegroup.projects.entity.Pessoa;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PessoaAdapter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PessoaResponse transformarPessoaResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento() != null ? formatter.format(pessoa.getDataNascimento()) : null)
                .gerente(pessoa.getGerente())
                .funcionario(pessoa.getFuncionario())
                .build();
    }

    public List<PessoaResponse> transformarPessoasResponse(List<Pessoa> pessoas) {
        if (pessoas == null) pessoas = new ArrayList<>();
        return pessoas.stream().map(this::transformarPessoaResponse).toList();
    }

}
