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
import java.util.Set;
import lombok.Data;

@Entity
@Data
public class Move {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "power")
  private int power;


  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "category", nullable = false)
  private String category;

  @ManyToMany
  @JoinTable(name = "move_type",
             joinColumns = @JoinColumn(name = "move_id"),
             inverseJoinColumns = @JoinColumn(name = "type_id"))
  private Set<Type> types;

}
