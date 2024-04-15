package br.com.codegroup.projects.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;
    @Column(name = "data_fim")
    private LocalDate dataFim;
    @Column(name = "descricao", length = 5000)
    private String descricao;
    @Column(name = "status", length = 45)
    private String status;
    @Positive
    @Column(name = "orcamento")
    private Double orcamento;
    @Column(name = "risco", length = 45)
    private String risco;
    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private List<Pessoa> funcionarios;

    public Projeto(Long id) {
        this.id = id;
    }
}


