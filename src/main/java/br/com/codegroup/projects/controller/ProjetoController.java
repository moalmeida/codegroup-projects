package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;


    @PostMapping
    public ResponseEntity<Projeto> salvar(@Valid @RequestBody Projeto projeto) {
        return ResponseEntity.status(201).body(projetoRepository.save(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @Valid @RequestBody Projeto entrada) {
        Optional<Projeto> entidade = this.projetoRepository.findById(id);

        if (entidade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        entidade.get().setNome(entrada.getNome());

        this.projetoRepository.save(entidade.get());
        return ResponseEntity.status(201).body(entidade.get());
    }


}
