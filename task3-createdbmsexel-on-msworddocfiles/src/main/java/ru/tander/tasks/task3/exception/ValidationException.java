package ru.tander.tasks.task3.exception;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 01.10.2015.
 * @version 1.0 Oct 2015
 */
public class ValidationException extends Exception {
  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(Throwable cause) {
    super(cause);
  }
}