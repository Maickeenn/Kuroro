package br.com.stratzord.kuroro.service;

import br.com.stratzord.kuroro.domain.dto.UserDto;
import br.com.stratzord.kuroro.domain.model.User;
import br.com.stratzord.kuroro.domain.parser.UserMapper;
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

  @Transactional(readOnly = true)
  public UserDto findById(Long id) {
    return UserMapper.INSTANCE.userToUserDto(userRepository.findById(id)
                                                           .orElseThrow(() -> new UserNotFoundException(
                                                               "No user found with id " + id)));
  }

  @Transactional(readOnly = true)
  public UserDto findByNickname(String nickname) {
    return UserMapper.INSTANCE.userToUserDto(userRepository.findByNickname(nickname)
                                                           .orElseThrow(() -> new UserNotFoundException(
                                                               "No user found with nickname " +
                                                               nickname)));
  }

  @Transactional
  public UserDto save(User user) {
    userRepository.findByNickname(user.getNickname()).ifPresent(existingUser -> {
      throw new UserAlreadyExistsException(
          "A user with nickname " + user.getNickname() + " already exists.");
    });
    return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
  }

  @Transactional
  public UserDto updateUser(Long id, User user) {
    User existingUser = userRepository.findById(id)
                                      .orElseThrow(() -> new UserNotFoundException(
                                          "No user found with id " + id));

    existingUser.setNickname(user.getNickname());
    existingUser.setPassword(user.getPassword());

    return UserMapper.INSTANCE.userToUserDto(userRepository.save(existingUser));
  }

  @Transactional
  public void deleteById(Long id) {
    userRepository.delete(userRepository.findById(id)
                                        .orElseThrow(() -> new UserNotFoundException(
                                            "No user found with id " + id)));
  }

}
