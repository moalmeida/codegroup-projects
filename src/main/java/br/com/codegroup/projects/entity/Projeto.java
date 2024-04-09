package br.com.codegroup.projects.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "projeto")
public class ProjetoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private PessoaEntity gerente;

}


