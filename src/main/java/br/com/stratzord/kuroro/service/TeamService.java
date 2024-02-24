package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Team;
import br.com.stratzord.kuroro.domain.model.TeamKuroro;
import br.com.stratzord.kuroro.domain.model.TeamResponse;
import br.com.stratzord.kuroro.domain.model.User;
import br.com.stratzord.kuroro.exception.TeamNotFoundException;
import br.com.stratzord.kuroro.exception.UserNotFoundException;
import br.com.stratzord.kuroro.infrastructurure.repository.TeamKuroroRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.TeamRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final TeamRepository teamRepository;
  private final UserRepository userRepository;
  private final TeamKuroroRepository teamKuroroRepository;

  public TeamService(TeamRepository teamRepository,
      UserRepository userRepository,
      TeamKuroroRepository teamKuroroRepository) {
    this.teamRepository = teamRepository;
    this.userRepository = userRepository;
    this.teamKuroroRepository = teamKuroroRepository;
  }

  private Team getTeam(Long id) {
    return teamRepository.findById(id)
                         .orElseThrow(() -> new TeamNotFoundException(
                             "No team found with id " + id));
  }

  public TeamResponse getKuroroTeam(Long id) {
    Team team = getTeam(id);
    List<Kuroro> kuroros = teamKuroroRepository.findKurorosByTeam(team);
    return new TeamResponse(team.getId(), team.getUser().getId(), team.getName(), new HashSet<>(kuroros));
  }

  public TeamResponse createTeam(String nickname, String name, List<Kuroro> kuroros) {
    User user = userRepository.findByNickname(nickname)
                              .orElseThrow(() -> new TeamNotFoundException(
                                  "No team found with id " + nickname));
    Team team = new Team();
    team.setUser(user);
    team.setName(name);
    team = teamRepository.save(team);

    for (Kuroro kuroro : kuroros) {
      TeamKuroro teamKuroro = new TeamKuroro();
      teamKuroro.setTeam(team);
      teamKuroro.setKuroro(kuroro);
      teamKuroroRepository.save(teamKuroro);
    }

    return getKuroroTeam(team.getId());
  }

  public Team editTeam(Long id, List<Kuroro> kuroros) {
    Team team = getTeam(id);
    teamKuroroRepository.deleteByTeam(team);

    for (Kuroro kuroro : kuroros) {
      TeamKuroro teamKuroro = new TeamKuroro();
      teamKuroro.setTeam(team);
      teamKuroro.setKuroro(kuroro);
      teamKuroroRepository.save(teamKuroro);
    }

    return team;
  }

  public void deleteTeam(Long id) {
    Team team = getTeam(id);
    teamRepository.delete(team);
  }

  public List<TeamResponse> getTeamsAndKurorosByUserId(Long userId) {
    User user = userRepository.findById(userId)
                              .orElseThrow(() -> new UserNotFoundException(
                                  "No user found with id " + userId));
    List<Team> teams = teamRepository.findAllByUser(user);
    List<TeamResponse> teamResponses = new ArrayList<>();
    for (Team team : teams) {
      List<Kuroro> kuroros = teamKuroroRepository.findKurorosByTeam(team);
      teamResponses.add(new TeamResponse(team.getId(), user.getId(),team.getName(), new HashSet<>(kuroros)));
    }
    return teamResponses;
  }
}
