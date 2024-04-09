package br.com.codegroup.projects.service;

import br.com.codegroup.projects.entity.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Pessoa save(Pessoa pessoa);

    Optional<Pessoa> findById(Long id);

    List<Pessoa> findAll();

    Pessoa update(Long id, Pessoa pessoa);

    void delete(Long id);
}
