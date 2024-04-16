package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.domain.adapter.PessoaAdapter;
import br.com.codegroup.projects.domain.adapter.ProjetoAdapter;
import br.com.codegroup.projects.domain.dto.ProjetoRequest;
import br.com.codegroup.projects.entity.Membros;
import br.com.codegroup.projects.entity.MembrosId;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.entity.Projeto;
import br.com.codegroup.projects.repository.MembrosRepository;
import br.com.codegroup.projects.repository.PessoaRepository;
import br.com.codegroup.projects.repository.ProjetoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final PessoaRepository pessoaRepository;
    private final ProjetoRepository projetoRepository;
    private final MembrosRepository membrosRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final PessoaAdapter pessoaAdapter = new PessoaAdapter();
    private final ProjetoAdapter projetoAdapter = new ProjetoAdapter();

    public ProjetoController(PessoaRepository pessoaRepository, ProjetoRepository projetoRepository, MembrosRepository membrosRepository) {
        this.pessoaRepository = pessoaRepository;
        this.projetoRepository = projetoRepository;
        this.membrosRepository = membrosRepository;
    }

    @GetMapping
    public String buscarTodos(Model model) {
        var projetos = projetoRepository.buscarTodos();
        model.addAttribute("projetos", projetoAdapter.transformarProjetosResponse(projetos));
        return "projetos/list";
    }

    @PostMapping("/remover/{id}")
    public String removerPorId(@PathVariable Long id) {
        membrosRepository.deletarTodosPorProjeto(id);
        projetoRepository.removerPorId(id);
        return "redirect:/projetos";
    }


    @PostMapping(value = "/cadastrar", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String salvar(@ModelAttribute ProjetoRequest request) {
        var gerente = pessoaRepository.buscarPorId(request.getGerente()).orElse(null);
        var dataInicio = request.getDataInicio() != null && !request.getDataInicio().isEmpty() ? LocalDate.parse(request.getDataInicio(), formatter) : null;
        var dataPrevisaoFim = request.getDataPrevisaoFim() != null && !request.getDataPrevisaoFim().isEmpty() ? LocalDate.parse(request.getDataPrevisaoFim(), formatter) : null;
        var dataFim = request.getDataFim() != null && !request.getDataFim().isEmpty() ? LocalDate.parse(request.getDataFim(), formatter) : null;
        var projeto = projetoRepository.buscarPorId(request.getId()).orElseGet(Projeto::new);

        projeto.setNome(request.getNome());
        projeto.setDescricao(request.getDescricao());
        projeto.setGerente(gerente);
        projeto.setDataInicio(dataInicio);
        projeto.setDataPrevisaoFim(dataPrevisaoFim);
        projeto.setDataFim(dataFim);
        projeto.setStatus(request.getStatus());
        projeto.setOrcamento((double) request.getOrcamento());
        projeto.setRisco(request.getRisco());

        projetoRepository.salvar(projeto);

        var funcionarioIds = new ArrayList<Long>();
        if (request.getFuncionarios() != null && !request.getFuncionarios().isEmpty()) {
            funcionarioIds.addAll(Arrays.stream(request.getFuncionarios().split(",")).map(Long::valueOf).toList());
        }
        var membros = new ArrayList<Membros>();
        for (Long id : funcionarioIds) {
            var membrosId = new MembrosId(projeto.getId(), id);
            var membrosPessoa = new Pessoa(id);
            membros.add(new Membros(membrosId, projeto, membrosPessoa));
        }
        membrosRepository.deletarTodosPorProjeto(projeto.getId());
        membrosRepository.salvarTodos(membros);

        return "redirect:/projetos";
    }

    @GetMapping("/cadastrar")
    public String formulario(Model model) {
        var gerentes = pessoaRepository.buscarTodosGerentes();
        var funcionarios = pessoaRepository.buscarTodosFuncionarios();
        var status = projetoRepository.buscarStatus();
        var riscos = projetoRepository.buscarRiscos();

        model.addAttribute("gerentes", pessoaAdapter.transformarPessoasResponse(gerentes));
        model.addAttribute("funcionarios", pessoaAdapter.transformarPessoasResponse(funcionarios));
        model.addAttribute("status", status);
        model.addAttribute("riscos", riscos);
        return "projetos/form";
    }

    @GetMapping("/cadastrar/{id}")
    public String formularioAlterar(Model model, @PathVariable Long id) {
        var gerentes = pessoaRepository.buscarTodosGerentes();
        var funcionarios = pessoaRepository.buscarTodosFuncionarios();
        var status = projetoRepository.buscarStatus();
        var riscos = projetoRepository.buscarRiscos();
        var projeto = projetoRepository.buscarPorId(id);

        projeto.ifPresent(value -> model.addAttribute("projeto", projetoAdapter.transformarProjetoResponse(value)));
        model.addAttribute("gerentes", pessoaAdapter.transformarPessoasResponse(gerentes));
        model.addAttribute("funcionarios", pessoaAdapter.transformarPessoasResponse(funcionarios));
        model.addAttribute("status", status);
        model.addAttribute("riscos", riscos);
        return "projetos/form";
    }

}
