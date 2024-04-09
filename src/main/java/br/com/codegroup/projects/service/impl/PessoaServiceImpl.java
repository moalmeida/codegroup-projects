package br.com.codegroup.projects.service.impl;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.service.PessoaService;
import br.com.codegroup.projects.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa not found for this id :: " + id));

        return pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa not found for this id :: " + id));

        pessoaRepository.delete(pessoa);
    }
}
