package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.ProjetoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        entidade.get().setDataInicio(entrada.getDataInicio());
        entidade.get().setDataPrevisaoFim(entrada.getDataPrevisaoFim());
        entidade.get().setDataFim(entrada.getDataFim());
        entidade.get().setDescricao(entrada.getDescricao());
        entidade.get().setStatus(entrada.getStatus());
        entidade.get().setOrcamento(entrada.getOrcamento());
        entidade.get().setRisco(entrada.getRisco());
        entidade.get().setGerente(entrada.getGerente());

        this.projetoRepository.save(entidade.get());
        return ResponseEntity.status(201).body(entidade.get());
    }


}
