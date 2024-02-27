package br.com.stratzord.kuroro.domain.model;

import java.util.List;
import lombok.Data;

@Data
public class TeamKuroroRequest {

  private long kuroroId;
  private List<Integer> moves;
  private BonusStats bonusStats;

}
