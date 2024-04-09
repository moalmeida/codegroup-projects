package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.service.impl.ProjetoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoServiceImpl projetoService;

    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public Projeto add(@RequestBody Projeto pessoa) {
        return projetoService.save(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> getAll() {
        List<Projeto> projetos = projetoService.findAll();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> update(@PathVariable Long id, @RequestBody Projeto projeto) {
        projetoService.update(id, projeto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetoService.delete(id);

        return ResponseEntity.accepted().build();
    }


}
