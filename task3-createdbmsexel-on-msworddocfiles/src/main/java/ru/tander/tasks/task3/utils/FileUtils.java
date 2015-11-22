/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 28.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.utils;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileUtils {
  private static final String EXT_SEPARATOR = ".";

  public static String getFileNameExt(String fileNameWithExt) {
    int dot = fileNameWithExt.lastIndexOf(EXT_SEPARATOR);
    return fileNameWithExt.substring(dot + 1);
  }
}
