package br.com.stratzord.kuroro.domain.model;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponse {
  private Long teamId;
  private String userNickname;
  private String name;
  private Set<TeamKuroroResponse> kuroros;
}
