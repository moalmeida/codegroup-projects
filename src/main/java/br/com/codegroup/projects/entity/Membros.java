package br.com.codegroup.projects.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "membros")
public class Membros implements Serializable {

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

}