package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.dto.MoveDto;
import br.com.stratzord.kuroro.domain.parser.MoveMapper;
import br.com.stratzord.kuroro.infrastructurure.repository.MoveRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

  private final static MoveMapper moveMapper = MoveMapper.INSTANCE;
  private final MoveRepository moveRepository;

  public MoveService(MoveRepository moveRepository) {
    this.moveRepository = moveRepository;
  }

  public List<MoveDto> getAllMoves() {

    return moveRepository.findAll().stream().map(moveMapper::moveToMoveDto).toList();
  }
}
