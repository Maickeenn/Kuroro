package br.com.stratzord.kuroro.domain.dto;

import java.util.Set;
import lombok.Data;

@Data
public class MoveDto {
  private Long id;
  private String name;
  private int power;
  private String description;
  private String category;
  private Set<TypeDto> types;
}
