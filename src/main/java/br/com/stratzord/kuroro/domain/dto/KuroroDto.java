package br.com.stratzord.kuroro.domain.dto;

import java.util.Set;
import lombok.Data;

@Data
public class KuroroDto {
  private Long id;
  private String name;
  private int hitPoints;
  private int attack;
  private int defense;
  private int magicAttack;
  private int magicDefense;
  private int speed;
  private String lore;
  private Set<TypeDto> types;

}
