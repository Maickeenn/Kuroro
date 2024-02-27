package br.com.stratzord.kuroro.infrastructurure.repository;

import br.com.stratzord.kuroro.domain.model.TeamKuroro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamKuroroRepository extends JpaRepository<TeamKuroro, Long> {


}
