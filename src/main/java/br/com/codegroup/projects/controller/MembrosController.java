package br.com.codegroup.projects.controller;


import br.com.codegroup.projects.entity.Membros;
import br.com.codegroup.projects.repository.MembrosRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/membros")
public class MembrosController {

    private final MembrosRepository membrosRepository;

    public MembrosController(MembrosRepository membrosRepository) {
        this.membrosRepository = membrosRepository;
    }

    @PostMapping
    public Membros adicionarMembros(@Valid @RequestBody Membros membros) {
        return membrosRepository.save(membros);
    }


}
