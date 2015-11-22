package ru.tander.tasks.task3.fileprocessor;

/**
 * @author Evgeny Dobrokvashin
 *         <p/>
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Сент. 2015
 */
public class FileProcessorException extends Exception {
  public FileProcessorException(String message) {
    super(message);
  }

  public FileProcessorException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileProcessorException(Throwable cause) {
    super(cause);
  }
}