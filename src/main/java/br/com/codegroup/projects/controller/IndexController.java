package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class IndexController {

    private final ProjetoRepository projetoRepository;
    private final PessoaRepository pessoaRepository;
    private final List<String> status = List.of(
            "em análise",
            "análise realizada",
            "análise aprovada",
            "iniciado",
            "planejado",
            "em andamento",
            "encerrado",
            "cancelado"
    );
    private final List<String> riscos = List.of(
            "baixo risco",
            "médio risco",
            "alto risco"
    );

    public IndexController(ProjetoRepository projetoRepository, PessoaRepository pessoaRepository) {
        this.projetoRepository = projetoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/projetos")
    public String listarProjetos(Model model) {
        model.addAttribute("projetos", this.projetoRepository.findAll());
        return "projetos/list";
    }

    @PostMapping("/projetos/cadastrar")
    public String incluirProjeto(Model model, @Valid @RequestBody Projeto projeto) {
        this.projetoRepository.save(projeto);
        return "redirect:/projetos";
    }

    @PostMapping("/projetos/remover/{id}")
    public String removerProjeto(@PathVariable Long id) {
        this.projetoRepository.deleteById(id);
        return "redirect:/projetos";
    }

    @GetMapping({"/projetos/formulario", "/projetos/formulario/{id}"})
    public String alterarProjeto(Model model, @PathVariable Long id) {
        var projeto = this.projetoRepository.findById(id);
        var gerentes = this.pessoaRepository.findAllByGerente(true);
        var funcionarios = this.pessoaRepository.findAllByFuncionario(true);

        model.addAttribute("gerentes", gerentes);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("status", status);
        model.addAttribute("riscos", riscos);
        projeto.ifPresent(value -> model.addAttribute("projeto", value));
        return "projetos/form";
    }


}
