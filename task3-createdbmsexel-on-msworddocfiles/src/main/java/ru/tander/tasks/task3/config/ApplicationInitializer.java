/*
* This class developed for realize read config data for app
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 22.09.2015
*
* Copyright (c) 2015 Tander, All Rights Reserved.
*/

package ru.tander.tasks.task3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tander.tasks.task3.exception.AppConfigException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 22.09.2015.
 * @version 1.0 Sep 2015
 */
public class ApplicationInitializer {
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);

  private static final String CONFIG_FILE_NAME = "./cfg/appConfig.properties";

  private static final String INPUT_FILES_DIR_PARAMNAME = "InputFiles.dir";

  private static final String OUTPUT_FILENAME_PARAMNAME = "Output.filename";

  private static Properties appConfigFile = null;

  private static void load() throws AppConfigException {
    try (FileInputStream fis = new FileInputStream(CONFIG_FILE_NAME)) {
      appConfigFile = new Properties();

      appConfigFile.load(fis);
    } catch (FileNotFoundException e) {
      LOG.error(String.format("Config file [%s] not found.", CONFIG_FILE_NAME), e);
      throw new AppConfigException(String.format("Config file [%s] not found.", CONFIG_FILE_NAME), e);
    } catch (IOException e) {
      LOG.error(String.format("Can't open or read from config file [%s].", CONFIG_FILE_NAME), e);
      throw new AppConfigException(String.format("Can't open or read from config file [%s].", CONFIG_FILE_NAME), e);
    }
  }

  public static void configure() throws AppConfigException {
    load();

    ApplicationConfig.InputFilesDir = appConfigFile.getProperty(INPUT_FILES_DIR_PARAMNAME);

    ApplicationConfig.OutputFilename = appConfigFile.getProperty(OUTPUT_FILENAME_PARAMNAME);
  }
}
