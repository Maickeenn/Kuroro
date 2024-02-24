package br.com.stratzord.kuroro.infrastructurure.repository;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KuroroRepository extends JpaRepository<Kuroro, Long> {
  Optional<Kuroro> findById(String id);
}
