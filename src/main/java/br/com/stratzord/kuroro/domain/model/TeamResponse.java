package br.com.stratzord.kuroro.domain.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamResponse {
  private Long teamId;
  private Long userId;
  private String name;
  private Set<Kuroro> kuroros;

}
