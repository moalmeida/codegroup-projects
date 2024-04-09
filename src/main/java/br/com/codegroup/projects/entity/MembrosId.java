package br.com.codegroup.projects.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MembrosId implements Serializable {

    @Column(name = "idprojeto")
    private Long idProjeto;

    @Column(name = "idpessoa")
    private Long idPessoa;

    // Default constructor
    public MembrosId() {}

    // Constructor with fields, getters, and setters
    public MembrosId(Long idProjeto, Long idPessoa) {
        this.idProjeto = idProjeto;
        this.idPessoa = idPessoa;
    }

    // Getters and setters

    // hashCode() and equals() methods
}