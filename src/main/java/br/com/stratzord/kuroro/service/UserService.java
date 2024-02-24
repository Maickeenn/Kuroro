package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.model.User;
import br.com.stratzord.kuroro.exception.UserAlreadyExistsException;
import br.com.stratzord.kuroro.exception.UserNotFoundException;
import br.com.stratzord.kuroro.infrastructurure.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User findById(Long id) {
    return userRepository.findById(id)
                         .orElseThrow(() -> new UserNotFoundException(
                             "No user found with id " + id));
  }

  @Transactional(readOnly = true)
  public User findByNickname(String nickname) {
    return userRepository.findByNickname(nickname)
                         .orElseThrow(() -> new UserNotFoundException(
                             "No user found with nickname " + nickname));
  }

  @Transactional
  public User save(User user) {
    userRepository.findByNickname(user.getNickname()).ifPresent(existingUser -> {
      throw new UserAlreadyExistsException(
          "A user with nickname " + user.getNickname() + " already exists.");
    });
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(Long id, User user) {
    User existingUser = userRepository.findById(id)
                                      .orElseThrow(() -> new UserNotFoundException(
                                          "No user found with id " + id));

    existingUser.setNickname(user.getNickname());
    existingUser.setPassword(user.getPassword());

    return userRepository.save(existingUser);
  }

  @Transactional
  public void deleteById(Long id) {
    User existingUser = findById(id);
    userRepository.delete(existingUser);
  }

}
