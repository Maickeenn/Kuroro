package br.com.stratzord.kuroro.domain.model;

import java.util.List;
import lombok.Data;

@Data
public class TeamRequest {

  private String nickname;
  private String name;
  private List<TeamKuroroRequest> teamKuroro;
}
