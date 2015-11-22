package ru.tander.tasks.task3.filefeeder;

import java.util.EventListener;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 29.09.2015.
 * @version 1.0 Sep 2015
 */
public interface FileFeederListener extends EventListener {
  void getFileFeedEventHandler(FileFeedEvent e);
}
