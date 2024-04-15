package br.com.codegroup.projects.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class MembrosId implements Serializable {
    private Long idprojeto;
    private Long idpessoa;

    public MembrosId(Long idprojeto, Long idpessoa) {
        this.idprojeto = idprojeto;
        this.idpessoa = idpessoa;
    }
}