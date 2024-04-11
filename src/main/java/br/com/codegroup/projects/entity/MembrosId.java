package br.com.codegroup.projects.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class MembrosId implements Serializable {

    @Column(name = "idprojeto")
    private Long idProjeto;

    @Column(name = "idpessoa")
    private Long idPessoa;

}