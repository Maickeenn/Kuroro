package br.com.stratzord.kuroro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Type {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long typeId;

  @Column(name = "type_name", nullable = false, unique = true)
  private String typeName;

  @ManyToMany(mappedBy = "types", fetch = FetchType.LAZY)
  private List<Kuroro> kuroros;

}
