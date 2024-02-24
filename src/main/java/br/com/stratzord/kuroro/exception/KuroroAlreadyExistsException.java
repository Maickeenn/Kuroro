package br.com.stratzord.kuroro.exception;

public class KuroroAlreadyExistsException extends RuntimeException {

  public KuroroAlreadyExistsException(String message) {
    super(message);
  }
}
