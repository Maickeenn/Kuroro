package br.com.stratzord.kuroro.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamKuroroResponse {
  private String kuroroId;
  private List<Long> moves;
  private BonusStats bonusStats;
}
