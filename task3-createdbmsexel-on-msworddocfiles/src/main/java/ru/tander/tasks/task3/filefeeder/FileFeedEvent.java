/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 29.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.filefeeder;

import java.util.EventObject;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 29.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileFeedEvent extends EventObject {
  private String fileName = null;

  public FileFeedEvent(Object source, String fileName) {
    super(source);

    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
