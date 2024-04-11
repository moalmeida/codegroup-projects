package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Pessoa> entidade = this.pessoaRepository.findById(id);

        if (entidade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        entidade.get().setNome(entrada.getNome());

        this.pessoaRepository.save(entidade.get());
        return ResponseEntity.status(201).body(entidade.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }

}
