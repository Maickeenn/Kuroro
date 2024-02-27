package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Move;
import br.com.stratzord.kuroro.domain.model.Team;
import br.com.stratzord.kuroro.domain.model.TeamKuroro;
import br.com.stratzord.kuroro.domain.model.TeamKuroroRequest;
import br.com.stratzord.kuroro.domain.model.TeamKuroroResponse;
import br.com.stratzord.kuroro.domain.model.TeamResponse;
import br.com.stratzord.kuroro.domain.model.User;
import br.com.stratzord.kuroro.exception.TeamNotFoundException;
import br.com.stratzord.kuroro.exception.UserNotFoundException;
import br.com.stratzord.kuroro.infrastructurure.repository.BonusStatsRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.KuroroRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.MoveRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.TeamKuroroRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.TeamRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final TeamRepository teamRepository;
  private final UserRepository userRepository;
  private final TeamKuroroRepository teamKuroroRepository;
  private final KuroroRepository kuroroRepository;
  private final MoveRepository moveRepository;
  private final BonusStatsRepository bonusStatsRepository;

  public TeamService(TeamRepository teamRepository,
      UserRepository userRepository,
      TeamKuroroRepository teamKuroroRepository,
      KuroroRepository kuroroRepository,
      MoveRepository moveRepository,
      BonusStatsRepository bonusStatsRepository) {
    this.teamRepository = teamRepository;
    this.userRepository = userRepository;
    this.teamKuroroRepository = teamKuroroRepository;
    this.kuroroRepository = kuroroRepository;
    this.moveRepository = moveRepository;
    this.bonusStatsRepository = bonusStatsRepository;
  }

  private Team getTeam(Long id) {
    return teamRepository.findById(id)
                         .orElseThrow(() -> new TeamNotFoundException(
                             "No team found with id " + id));
  }

  public TeamResponse getKuroroTeam(Long id) {
    Team team = getTeam(id);
    return mapTeamToTeamResponse(team);
  }

  public Team createTeam(String nickname, String name, List<TeamKuroroRequest> kuroros) {
    User user = userRepository.findByNickname(nickname)
                              .orElseThrow(() -> new UserNotFoundException(
                                  "No user found with nickname " + nickname));
    Team team = new Team();
    team.setUser(user);
    team.setName(name);
    team = teamRepository.save(team);

    for (TeamKuroroRequest kuroro : kuroros) {
      TeamKuroro teamKuroro = new TeamKuroro();
      teamKuroro.setKuroro(kuroroRepository.findById(kuroro.getKuroroId())
                                           .orElseThrow(() -> new TeamNotFoundException(
                                               "No kuroro found with id " + kuroro)));
      Set<Move> moves = kuroro.getMoves()
                              .stream()
                              .map(move -> moveRepository.findById(Long.valueOf(move))
                                                         .orElseThrow(() -> new TeamNotFoundException(
                                                             "No move found with id " + move)))
                              .collect(Collectors.toSet());
      teamKuroro.setMoves(moves);
      teamKuroro.setBonusStats(bonusStatsRepository.save(kuroro.getBonusStats()));
      teamKuroroRepository.save(teamKuroro);
    }

    return getTeam(team.getId());
  }

  public Team editTeam(Long id, List<Kuroro> kuroros) {
    Team team = getTeam(id);
    teamKuroroRepository.deleteAll(team.getTeamKuroros());

    for (Kuroro kuroro : kuroros) {
      TeamKuroro teamKuroro = new TeamKuroro();
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
    List<Team> allByUser = teamRepository.findAllByUser(user);
    return allByUser.stream()
                    .map(this::mapTeamToTeamResponse)
                    .toList();
  }

  public TeamResponse mapTeamToTeamResponse(Team team) {
    Set<TeamKuroroResponse> kuroroResponses = team.getTeamKuroros()
                                 .stream()
                                 .map(this::mapTeamKuroroToTeamKuroroResponse)
                                 .collect(Collectors.toSet());

    return TeamResponse.builder()
                       .teamId(team.getId())
                       .userId(team.getUser().getId())
                       .name(team.getName())
                       .kuroros(kuroroResponses)
                       .build();
  }

  public TeamKuroroResponse mapTeamKuroroToTeamKuroroResponse(TeamKuroro teamKuroro) {
    return TeamKuroroResponse.builder()
                             .kuroroId(teamKuroro.getKuroro().getInternalId())
                             .moves(teamKuroro.getMoves()
                                              .stream()
                                              .map(Move::getId)
                                              .toList())
                             .bonusStats(teamKuroro.getBonusStats())
                             .build();
  }
}
