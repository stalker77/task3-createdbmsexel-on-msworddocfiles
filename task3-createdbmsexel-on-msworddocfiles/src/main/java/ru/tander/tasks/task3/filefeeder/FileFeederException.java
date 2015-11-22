package ru.tander.tasks.task3.filefeeder;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileFeederException extends Exception {
  public FileFeederException(String message) {
    super(message);
  }

  public FileFeederException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileFeederException(Throwable cause) {
    super(cause);
  }
}