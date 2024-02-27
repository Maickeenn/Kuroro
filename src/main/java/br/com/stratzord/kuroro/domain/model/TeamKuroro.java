package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Set;
import lombok.Data;

//Slot
@Entity(name = "team_kuroro")
@Data
public class TeamKuroro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @ManyToOne
  @JoinColumn(name = "kuroro_id", nullable = false)
  private Kuroro kuroro;

  @ManyToMany
  @JoinTable(name = "move_kuroro",
             joinColumns = @JoinColumn(name = "team_kuroro_id"),
             inverseJoinColumns = @JoinColumn(name = "move_id"))
  private Set<Move> moves;

  @ManyToMany
  @JoinTable(name = "bonus_stats_kuroro",
             joinColumns = @JoinColumn(name = "team_kuroro_id"),
             inverseJoinColumns = @JoinColumn(name = "bonus_stats_id"))
  private Set<BonusStats> bonusStats;
}
