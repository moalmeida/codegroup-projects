package br.com.codegroup.projects.service;

import br.com.codegroup.projects.entity.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    Projeto save(Projeto projeto);

    Optional<Projeto> findById(Long id);

    List<Projeto> findAll();

    Projeto update(Long id, Projeto projeto);

    void delete(Long id);
}
