package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Kuroro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "internal_id", nullable = false, unique = true)
  private Long internalId;

  @Column(name = "id", nullable = false, unique = true)
  private String id;

  @Column(name = "name", nullable = false)
  private String name;


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

  @Column(length = 1000)
  private String lore;

  @ManyToMany
  @JoinTable(
      name = "kuroro_type",
      joinColumns = @JoinColumn(name = "kuroro_id"),
      inverseJoinColumns = @JoinColumn(name = "type_id")
  )
  private List<Type> types;

}
