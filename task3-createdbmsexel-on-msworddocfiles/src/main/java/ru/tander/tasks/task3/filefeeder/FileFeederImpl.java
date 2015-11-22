/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 28.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.filefeeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tander.tasks.task3.utils.FileUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileFeederImpl extends SimpleFileVisitor<Path> implements FileFeeder {
  private static final Logger LOG = LoggerFactory.getLogger(FileFeederImpl.class);

  private String startDir = null;

  private int fileCounter = 0;

  public int getFileCounter() {
    return fileCounter;
  }

  private static final List<String> FILE_EXT_TO_WORK = Arrays.asList("DOC", "DOCX");

  private Set<FileFeederListener> getFileFeederListeners;

  public FileFeederImpl() {
    getFileFeederListeners = new HashSet<>();
  }

  public void processFileFeed() throws FileFeederException {
    Path startPath = Paths.get(startDir);

    try {
      Files.walkFileTree(startPath, this);
    } catch (IOException e) {
      LOG.error("Error during get file list.", e);
      throw new FileFeederException("Error during get file list.", e);
    }
  }

  public void setStartDir(String startDir) {
    this.startDir = startDir;
  }

  public int getFileCount() {
    return getFileCounter();
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    String fileName = file.toString();
    String fileExt = FileUtils.getFileNameExt(fileName).toUpperCase();

    if (attr.isRegularFile() &&
            //file.getFileName().toString().equals("S1-FGF03A-01.xml")) {
        FILE_EXT_TO_WORK.indexOf(fileExt) >= 0) {
      FileFeedEvent fileFeedEvent = new FileFeedEvent(this, fileName);
      onRaiseFileFeedEvent(fileFeedEvent);
      fileCounter = getFileCounter() + 1;
    }

    return FileVisitResult.CONTINUE;
  }

  protected void onRaiseFileFeedEvent(FileFeedEvent e) {
    for (FileFeederListener getFileFeederListener : getFileFeederListeners) {
      getFileFeederListener.getFileFeedEventHandler(e);
    }
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException e) {
    if (e instanceof FileSystemLoopException) {
      LOG.error("cycle detected: " + file, e);
    }
    else {
      LOG.error("", e);
    }
    return FileVisitResult.CONTINUE;
  }

  public void addFileFeedListener(FileFeederListener listener) {
    this.getFileFeederListeners.add(listener);
  }

  public void removeFileFeedListener(FileFeederListener listener) {
    this.getFileFeederListeners.remove(listener);
  }
}
