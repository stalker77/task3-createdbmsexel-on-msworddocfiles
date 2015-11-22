/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 28.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.fileprocessor;

import ru.tander.tasks.task3.config.ApplicationConfig;
import ru.tander.tasks.task3.dataaggregator.DataAggregator;
import ru.tander.tasks.task3.filefeeder.FileFeeder;
import ru.tander.tasks.task3.filefeeder.FileFeederException;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileProcessorManager {
  private FileFeeder fileFeeder = null;

  private FileProcessor fileProcessor = null;

  private DataAggregator dataAggregator = null;

  public void run() throws FileFeederException {
    System.out.println("Word file data collector.");

    fileFeeder.setStartDir(ApplicationConfig.InputFilesDir);
    fileFeeder.addFileFeedListener(fileProcessor);
    try {
      System.out.format("Begin file feed ...%n");

      fileFeeder.processFileFeed();

      if (fileFeeder.getFileCount() > 0) {
        dataAggregator.saveDataInExcelFile(ApplicationConfig.OutputFilename);
        System.out.format("%nResult file %s saved.%n", ApplicationConfig.OutputFilename);
      }

      System.out.format("%nProcessed %d word files.%n%n", fileFeeder.getFileCount());
    } finally {
      fileFeeder.removeFileFeedListener(fileProcessor);
    }
  }

  public void setFileFeeder(FileFeeder fileFeeder) {
    this.fileFeeder = fileFeeder;
  }

  public void setFileProcessor(FileProcessor fileProcessor) {
    this.fileProcessor = fileProcessor;
  }

  public void setDataAggregator(DataAggregator dataAggregator) {
    this.dataAggregator = dataAggregator;
  }
}
