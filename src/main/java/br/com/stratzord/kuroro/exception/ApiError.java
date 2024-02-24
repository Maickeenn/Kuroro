package br.com.stratzord.kuroro.exception;

import lombok.Data;

@Data
public class ApiError {

  private int code;
  private String message;

  public ApiError(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
