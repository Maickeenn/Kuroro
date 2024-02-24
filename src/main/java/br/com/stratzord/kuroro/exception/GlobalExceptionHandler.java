package br.com.stratzord.kuroro.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({UserNotFoundException.class, KuroroNotFoundException.class})
  public ResponseEntity<ApiError> handleCreatureNotFoundException(RuntimeException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({UserAlreadyExistsException.class, KuroroAlreadyExistsException.class})
  public ResponseEntity<ApiError> handleCreatureAlreadyExistsException(RuntimeException ex) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ApiError> handleDataAccessException(DataAccessException ex) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while accessing the database.");
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(QueryTimeoutException.class)
  public ResponseEntity<ApiError> handleQueryTimeoutException(QueryTimeoutException ex) {
    ApiError apiError = new ApiError(HttpStatus.REQUEST_TIMEOUT.value(), "The database query timed out.");
    return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGeneralException(Exception ex) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred.");
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
