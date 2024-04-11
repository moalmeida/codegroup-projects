package br.com.codegroup.projects.repository;

import br.com.codegroup.projects.entity.Membros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembrosRepository extends JpaRepository<Membros, Long> {

}
