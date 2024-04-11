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
@IdClass(MembrosId.class)
public class Membros implements Serializable {

    @Id
    @Column(name = "idprojeto")
    private Long idProjeto;

    @Id
    @Column(name = "idpessoa")
    private Long idPessoa;

}