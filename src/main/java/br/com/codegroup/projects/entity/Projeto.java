package br.com.codegroup.projects.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private List<Pessoa> funcionarios = new ArrayList<>();

    public boolean isPermitidoRemover() {
        return Stream.of("iniciado", "em andamento", "encerrado").noneMatch(s -> s.equalsIgnoreCase(this.status));
    }

}


