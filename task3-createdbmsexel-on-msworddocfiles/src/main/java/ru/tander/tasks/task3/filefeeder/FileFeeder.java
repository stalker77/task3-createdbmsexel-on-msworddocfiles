package ru.tander.tasks.task3.filefeeder;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public interface FileFeeder {
  void processFileFeed() throws FileFeederException;

  void setStartDir(String startDir);

  int getFileCount();

  void addFileFeedListener(FileFeederListener listener);

  void removeFileFeedListener(FileFeederListener listener);
}
