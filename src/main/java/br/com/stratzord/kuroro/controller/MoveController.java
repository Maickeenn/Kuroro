package br.com.stratzord.kuroro.controller;

import br.com.stratzord.kuroro.domain.model.Move;
import br.com.stratzord.kuroro.service.KuroroService;
import br.com.stratzord.kuroro.service.MoveService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/move")
public class MoveController {

  private final MoveService moveService;

  public MoveController(MoveService moveService) {
    this.moveService = moveService;
  }

  @GetMapping
  public ResponseEntity<List<Move>> getAllCreatures() {
    return new ResponseEntity<>(moveService.getAllMoves(), HttpStatus.OK);
  }

}
