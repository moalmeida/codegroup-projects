package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/projetos/cadastrar")
    public String incluirProjeto(Model model) {
        model.addAttribute("gerentes", this.pessoaRepository.findAllByGerente(true));
        model.addAttribute("funcionarios", this.pessoaRepository.findAllByFuncionario(true));
        model.addAttribute("status", status);
        model.addAttribute("riscos", riscos);
        return "projetos/edit";
    }

    @GetMapping("/projetos/cadastrar/:id")
    public String alterarProjeto(Model model, @PathVariable Long id) {
        model.addAttribute("gerentes", this.pessoaRepository.findAllByGerente(true));
        model.addAttribute("funcionarios", this.pessoaRepository.findAllByFuncionario(true));
        model.addAttribute("status", status);
        model.addAttribute("riscos", riscos);
        model.addAttribute("projeto", this.projetoRepository.findById(id));
        return "projetos/edit";
    }


}
