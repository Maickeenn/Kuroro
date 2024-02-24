package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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
}
