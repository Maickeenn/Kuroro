package br.com.stratzord.kuroro.infrastructurure.repository;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KuroroRepository extends JpaRepository<Kuroro, Long> {

  @Query("SELECT k FROM Kuroro k WHERE k.id = :id")
  Optional<Kuroro> findById(String id);

}
