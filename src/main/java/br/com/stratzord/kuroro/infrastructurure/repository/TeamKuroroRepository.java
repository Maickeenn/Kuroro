package br.com.stratzord.kuroro.infrastructurure.repository;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Team;
import br.com.stratzord.kuroro.domain.model.TeamKuroro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TeamKuroroRepository extends JpaRepository<TeamKuroro, Long> {

  @Query("SELECT tk.kuroro FROM team_kuroro tk WHERE tk.team = :team")
  List<Kuroro> findKurorosByTeam(@Param("team") Team team);

  @Modifying
  @Transactional
  void deleteByTeam(Team team);
}
