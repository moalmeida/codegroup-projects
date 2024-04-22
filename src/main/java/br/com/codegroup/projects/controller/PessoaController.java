package br.com.codegroup.projects.controller;

import br.com.codegroup.projects.domain.adapter.PessoaAdapter;
import br.com.codegroup.projects.domain.dto.PessoaRequest;
import br.com.codegroup.projects.domain.dto.PessoaResponse;
import br.com.codegroup.projects.entity.Pessoa;
import br.com.codegroup.projects.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final PessoaAdapter pessoaAdapter = new PessoaAdapter();

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PessoaResponse>> buscarTodos() {
        List<Pessoa> pessoas = pessoaRepository.buscarTodos();
        var response = pessoaAdapter.transformarPessoasResponse(pessoas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return pessoaRepository
                .buscarPorId(id)
                .map(pessoa -> ResponseEntity.ok(pessoaAdapter.transformarPessoaResponse(pessoa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PessoaResponse> salvar(@Valid @RequestBody PessoaRequest request) {
        var dataNascimento = request.getDataNascimento() != null ? LocalDate.parse(request.getDataNascimento(), formatter) : null;
        var pessoa = new Pessoa();

        pessoa.setNome(request.getNome());
        pessoa.setCpf(request.getCpf());
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setGerente(request.getGerente());
        pessoa.setFuncionario(request.getFuncionario());

        var response = pessoaAdapter.transformarPessoaResponse(pessoaRepository.salvar(pessoa));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequest request) {
        Optional<Pessoa> entidade = pessoaRepository.buscarPorId(id);
        if (entidade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var dataNascimento = request.getDataNascimento() != null ? LocalDate.parse(request.getDataNascimento(), formatter) : null;
        var pessoa = entidade.get();

        pessoa.setNome(request.getNome());
        pessoa.setCpf(request.getCpf());
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setGerente(request.getGerente());
        pessoa.setFuncionario(request.getFuncionario());

        var response = pessoaAdapter.transformarPessoaResponse(pessoaRepository.salvar(pessoa));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removerPorId(@PathVariable Long id) {
        pessoaRepository.removerPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio")
    public void buscarRelatorio(HttpServletResponse response) throws JRException, IOException {
        var parameters = new HashMap<String, Object>();
        var pessoas = pessoaRepository.buscarTodos();
        var pessoasDTO = pessoaAdapter.transformarPessoasResponse(pessoas);

        InputStream inputStream = getClass().getResourceAsStream("/pessoas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(pessoasDTO));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=projetos.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }


}
