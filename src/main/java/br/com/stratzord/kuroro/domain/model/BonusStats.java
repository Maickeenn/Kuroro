package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "bonus_stats")
@Data
public class BonusStats {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "hit_points")
  private int hitPoints;

  @Column(name = "attack")
  private int attack;

  @Column(name = "defense")
  private int defense;

  @Column(name = "magic_attack")
  private int magicAttack;

  @Column(name = "magic_defense")
  private int magicDefense;

  @Column(name = "speed")
  private int speed;

}
