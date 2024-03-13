package br.com.stratzord.kuroro.controller;


import br.com.stratzord.kuroro.domain.dto.UserDto;
import br.com.stratzord.kuroro.domain.model.User;
import br.com.stratzord.kuroro.service.UserService;
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
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
  }

  @GetMapping("/nickname/{nickname}")
  public ResponseEntity<UserDto> getUserByNickname(@PathVariable String nickname) {
    return new ResponseEntity<>(userService.findByNickname(nickname), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserDto> saveUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user) {
    return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
