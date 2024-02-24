package br.com.stratzord.kuroro.controller;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Team;
import br.com.stratzord.kuroro.domain.model.TeamRequest;
import br.com.stratzord.kuroro.domain.model.TeamResponse;
import br.com.stratzord.kuroro.service.TeamService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

  private final TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping
  public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest teamRequest) {
    TeamResponse team = teamService.createTeam(teamRequest.getNickname(), teamRequest.getName(), teamRequest.getKuroros());
    return new ResponseEntity<>(team, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamResponse> getTeam(@PathVariable Long id) {
    TeamResponse team = teamService.getKuroroTeam(id);
    return ResponseEntity.ok(team);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Team> editTeam(@PathVariable Long id, @RequestBody List<Kuroro> kuroros) {
    Team team = teamService.editTeam(id, kuroros);
    return ResponseEntity.ok(team);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
    teamService.deleteTeam(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<TeamResponse>> getTeamsAndKurorosByUserId(@PathVariable Long userId) {
    List<TeamResponse> teamResponses = teamService.getTeamsAndKurorosByUserId(userId);
    return ResponseEntity.ok(teamResponses);
  }
}
