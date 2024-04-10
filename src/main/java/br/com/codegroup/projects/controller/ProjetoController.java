package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.controller.exceptions.ResourceNotFoundException;
import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> buscarTodos() {
        var buscarTodos = this.projetoRepository.findAll();
        return ResponseEntity.ok(buscarTodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return this.projetoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Projeto salvar(@Valid @RequestBody Projeto pessoa) {
        return this.projetoRepository.saveAndFlush(pessoa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @Valid @RequestBody Projeto entrada) {

        Projeto entidade = this.projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found for this id :: " + id));

        entidade.setNome(entrada.getNome());
        entidade.setDataInicio(entrada.getDataInicio());
        entidade.setDataPrevisaoFim(entrada.getDataPrevisaoFim());
        entidade.setDataFim(entrada.getDataFim());
        entidade.setDescricao(entrada.getDescricao());
        entidade.setStatus(entrada.getStatus());
        entidade.setOrcamento(entrada.getOrcamento());
        entidade.setRisco(entrada.getRisco());
        entidade.setGerente(entrada.getGerente());

        this.projetoRepository.saveAndFlush(entidade);
        return ResponseEntity.status(201).body(entidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        this.projetoRepository.deleteById(id);
    }


}
