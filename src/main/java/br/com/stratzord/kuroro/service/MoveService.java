package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.model.Move;
import br.com.stratzord.kuroro.infrastructurure.repository.MoveRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

  private MoveRepository moveRepository;

  public MoveService(MoveRepository moveRepository) {
    this.moveRepository = moveRepository;
  }

  public List<Move> getAllMoves() {
    return moveRepository.findAll();
  }
}
