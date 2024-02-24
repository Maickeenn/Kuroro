package br.com.stratzord.kuroro.controller;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.service.KuroroService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kuroro")
public class KuroroController {

  private final KuroroService kuroroService;

  public KuroroController(KuroroService kuroroService) {
    this.kuroroService = kuroroService;
  }

  @GetMapping
  public ResponseEntity<List<Kuroro>> getAllCreatures() {
    return new ResponseEntity<>(kuroroService.getAllCreatures(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Kuroro> getCreatureById(@PathVariable String id) {
    return new ResponseEntity<>(kuroroService.getCreatureById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Kuroro> saveCreature(@RequestBody Kuroro kuroro) {
    return new ResponseEntity<>(kuroroService.saveCreature(kuroro), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Kuroro> updateCreature(@PathVariable String id, @RequestBody Kuroro kuroro) {
    return new ResponseEntity<>(kuroroService.updateCreature(id, kuroro), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCreature(@PathVariable String id) {
    kuroroService.deleteCreature(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
