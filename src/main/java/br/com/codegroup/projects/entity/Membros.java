package br.com.codegroup.projects.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "membros")
public class Membros {
    @EmbeddedId
    private MembrosId id;
    @ManyToOne
    @MapsId("idprojeto")
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;
    @ManyToOne
    @MapsId("idpessoa")
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;
}