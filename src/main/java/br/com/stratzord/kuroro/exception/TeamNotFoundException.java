package br.com.stratzord.kuroro.exception;

public class TeamNotFoundException extends RuntimeException{

  public TeamNotFoundException(String message) {
    super(message);
  }
}
