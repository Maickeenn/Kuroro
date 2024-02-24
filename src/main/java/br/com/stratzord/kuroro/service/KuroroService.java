package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Type;
import br.com.stratzord.kuroro.exception.KuroroAlreadyExistsException;
import br.com.stratzord.kuroro.exception.KuroroNotFoundException;
import br.com.stratzord.kuroro.infrastructurure.repository.KuroroRepository;
import br.com.stratzord.kuroro.infrastructurure.repository.TypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KuroroService {

  private final KuroroRepository kuroroRepository;
  private final TypeRepository typeRepository;

  public KuroroService(KuroroRepository kuroroRepository, TypeRepository typeRepository) {
    this.kuroroRepository = kuroroRepository;
    this.typeRepository = typeRepository;
  }

  @Transactional(readOnly = true)
  public List<Kuroro> getAllCreatures() {
    List<Kuroro> creatures = kuroroRepository.findAll();
    if (creatures.isEmpty()) {
      throw new KuroroNotFoundException("No creatures found.");
    }
    return creatures;
  }

  @Transactional(readOnly = true)
  public Kuroro getCreatureById(String id) {
    return kuroroRepository.findById(id)
                           .orElseThrow(() -> new KuroroNotFoundException(
                               "No creature found with id " + id));
  }

  @Transactional
  public Kuroro saveCreature(Kuroro kuroro) throws KuroroAlreadyExistsException {
    kuroroRepository.findById(kuroro.getId())
                    .ifPresent(value -> {
                      throw new KuroroAlreadyExistsException(
                          "A kuroro with id " + kuroro.getId() + " already exists.");
                    });

    kuroro.setTypes(updateTypes(kuroro.getTypes()));

    return kuroroRepository.save(kuroro);
  }

  @Transactional
  public Kuroro updateCreature(String id, Kuroro kuroro) {
    Kuroro existingKuroro = kuroroRepository.findById(id)
                                            .orElseThrow(() -> new KuroroNotFoundException(
                                                "No creature found with id " + id));

    existingKuroro.setName(kuroro.getName());
    existingKuroro.setHitPoints(kuroro.getHitPoints());
    existingKuroro.setAttack(kuroro.getAttack());
    existingKuroro.setDefense(kuroro.getDefense());
    existingKuroro.setMagicAttack(kuroro.getMagicAttack());
    existingKuroro.setMagicDefense(kuroro.getMagicDefense());
    existingKuroro.setSpeed(kuroro.getSpeed());
    existingKuroro.setLore(kuroro.getLore());
    existingKuroro.setTypes(updateTypes(kuroro.getTypes()));

    return kuroroRepository.save(existingKuroro);
  }

  @Transactional
  public void deleteCreature(String id) {
    Kuroro existingCreature = kuroroRepository.findById(id)
                                              .orElseThrow(() -> new KuroroNotFoundException(
                                                  "No creature found with id " + id));
    kuroroRepository.deleteById(existingCreature.getInternalId());
  }

  private List<Type> updateTypes(List<Type> types) {
    types.forEach(type -> {
      Type existingType = typeRepository.findByTypeName(type.getTypeName())
                                        .orElseGet(() -> typeRepository.save(type));
      type.setTypeId(existingType.getTypeId());
    });

    return types;
  }
}
