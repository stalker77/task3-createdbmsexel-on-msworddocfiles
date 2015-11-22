package ru.tander.tasks.task3.fileprocessor;

import ru.tander.tasks.task3.filefeeder.FileFeederListener;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public interface FileProcessor extends FileFeederListener {
  void process() throws FileProcessorException;
}
