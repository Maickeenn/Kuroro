package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//Slot
@Entity(name = "team_kuroro")
@Data
public class TeamKuroro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  @Fetch(FetchMode.JOIN)
  private Team team;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kuroro_id", nullable = false)
  @Fetch(FetchMode.JOIN)
  private Kuroro kuroro;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "move_kuroro",
             joinColumns = @JoinColumn(name = "team_kuroro_id"),
             inverseJoinColumns = @JoinColumn(name = "move_id"))
  @Fetch(FetchMode.JOIN)
  private Set<Move> moves;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "bonus_stats_id", nullable = false)
  @Fetch(FetchMode.JOIN)
  private BonusStats bonusStats;
}
