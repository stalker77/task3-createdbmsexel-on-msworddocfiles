/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 30.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.dataaggregator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tander.tasks.task3.entity.WordFileData;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 30.09.2015.
 * @version 1.0 Sep 2015
 */
public class DataAggregatorImpl implements DataAggregator {
  private static final Logger LOG = LoggerFactory.getLogger(DataAggregatorImpl.class);

  private XSSFWorkbook xssfWorkbook = null;

  private XSSFSheet sheet = null;

  private int rowId = 0;

  public DataAggregatorImpl() {
    xssfWorkbook = new XSSFWorkbook();
    sheet = xssfWorkbook.createSheet("Data");

    putHeaderInExcelFile();
  }

  private void putHeaderInExcelFile() {
    XSSFRow row = sheet.createRow(rowId++);
    int cellId = 0;

    row.createCell(cellId++).setCellValue(WordFileData.ADDRESS_FLD_NAME);
    row.createCell(cellId++).setCellValue(WordFileData.COMPNAME_FLD_NAME);
    row.createCell(cellId++).setCellValue(WordFileData.PHONE_FLD_NAME);
    row.createCell(cellId++).setCellValue(WordFileData.EMPNAME_FLD_NAME);
    row.createCell(cellId++).setCellValue(WordFileData.POSITION_FLD_NAME);
    row.createCell(cellId).setCellValue(WordFileData.CELLPHONE_FLD_NAME);
  }

  public void collectDataInExcelFile(WordFileData data) {
    XSSFRow row = sheet.createRow(rowId++);
    int cellId = 0;

    row.createCell(cellId++).setCellValue(data.getAddress());
    row.createCell(cellId++).setCellValue(data.getCompanyName());
    row.createCell(cellId++).setCellValue(data.getPhone());
    row.createCell(cellId++).setCellValue(data.getEmployeeName());
    row.createCell(cellId++).setCellValue(data.getPosition());
    row.createCell(cellId).setCellValue(data.getCellPhone());
  }

  public void saveDataInExcelFile(String fileName) {
    Path outputFile = Paths.get(fileName);
    if (Files.exists(outputFile)) {
      try {
        Files.delete(outputFile);
      } catch (IOException e) {
        LOG.error("Error during delete existing output file:" + fileName, e);
      }
    }

    try (OutputStream bufferedWriter = Files.newOutputStream(outputFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
      xssfWorkbook.write(bufferedWriter);
      xssfWorkbook.close();
    } catch (IOException e) {
      LOG.error("Error during write data to Excel file " + fileName, e);
    }
  }
}
