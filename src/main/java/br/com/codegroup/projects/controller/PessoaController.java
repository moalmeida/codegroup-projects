package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.controller.exceptions.ResourceNotFoundException;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa salvar(@Valid @RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa entrada) {
        Pessoa entidade = this.pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found for this id :: " + id));
        entidade.setNome(entrada.getNome());
        this.pessoaRepository.save(entidade);
        return ResponseEntity.status(201).body(entidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }

}
