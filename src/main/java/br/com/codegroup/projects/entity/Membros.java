package br.com.codegroup.projects.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membros")
public class Membros {

    @EmbeddedId
    private MembrosId id;

    @ManyToOne
    @MapsId("idProjeto") // This maps the idProjeto attribute in MembrosId to this relationship
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;

    @ManyToOne
    @MapsId("idPessoa") // This maps the idPessoa attribute in MembrosId to this relationship
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    // Default constructor
    public Membros() {
    }

    // Constructors, Getters, and Setters

    public Membros(MembrosId id) {
        this.id = id;
    }

    // Additional getters and setters for projeto and pessoa
}