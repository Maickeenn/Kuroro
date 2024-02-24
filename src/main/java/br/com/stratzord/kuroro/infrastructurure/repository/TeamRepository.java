package br.com.stratzord.kuroro.infrastructurure.repository;

import br.com.stratzord.kuroro.domain.model.Team;
import br.com.stratzord.kuroro.domain.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

  List<Team> findAllByUser(User user);


}
