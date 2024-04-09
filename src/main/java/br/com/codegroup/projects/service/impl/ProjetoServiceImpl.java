package br.com.codegroup.projects.service.impl;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.service.ProjetoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    public List<Projeto> findAll() {
        return new ArrayList<>();
    }

    public Projeto save(Projeto projeto) {
        return null;
    }

    public Optional<Projeto> findById(Long id) {
        return Optional.empty();
    }

    public Projeto update(Long id, Projeto projeto) {
        return null;
    }

    public void delete(Long id) {
//        return null;
    }
}
