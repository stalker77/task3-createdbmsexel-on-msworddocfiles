/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 28.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.fileprocessor;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tander.tasks.task3.dataaggregator.DataAggregator;
import ru.tander.tasks.task3.entity.ParsedRowData;
import ru.tander.tasks.task3.entity.WordFileData;
import ru.tander.tasks.task3.exception.ValidationException;
import ru.tander.tasks.task3.filefeeder.FileFeedEvent;
import ru.tander.tasks.task3.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 28.09.2015.
 * @version 1.0 Sep 2015
 */
public class FileProcessorImpl implements FileProcessor {
  private static final Logger LOG = LoggerFactory.getLogger(FileProcessorImpl.class);

  private String inputFileName = null;

  private DataAggregator dataAggregator = null;

  public void setDataAggregator(DataAggregator dataAggregator) {
    this.dataAggregator = dataAggregator;
  }

  public void process() throws FileProcessorException {
    if (null == inputFileName) {
      throw new NullPointerException("inputFileName");
    }

    String fileExt = FileUtils.getFileNameExt(inputFileName).toUpperCase();

    System.out.format("Processing source: %s...", inputFileName);

    switch (fileExt) {
      case "DOC":
        processDocFile();
        break;
      case "DOCX":
        processDocxFile();
        break;
      default:
        throw new FileProcessorException("Unsupported file type.");
    }

    System.out.format(" Done.%n");
  }

  private void processDocFile() throws FileProcessorException {
    Path inputFile = Paths.get(inputFileName);

    try (InputStream doc = Files.newInputStream(inputFile)) {
      HWPFDocument hwpfDocument = new HWPFDocument(doc);

      Range range = hwpfDocument.getRange();
      WordFileData data = new WordFileData();
      for (int i = 0; i < range.numParagraphs(); i++) {
        Paragraph paragraph = range.getParagraph(i);
        ParsedRowData parsedRowData = parseDocumentDataRow(paragraph.text());
        try {
          data.setMappedValue(parsedRowData.getFieldName(), parsedRowData.getFieldValue());
        } catch (ValidationException e) {
          LOG.error("", e);
        }
      }

      dataAggregator.collectDataInExcelFile(data);
    } catch (IOException e) {
      throw new FileProcessorException("Error during open file.", e);
    }
  }

  private void processDocxFile() throws FileProcessorException {
    Path inputFile = Paths.get(inputFileName);

    try (InputStream doc = Files.newInputStream(inputFile);
         XWPFDocument xwpfDocument = new XWPFDocument(doc)) {
      WordFileData data = new WordFileData();
      for(XWPFParagraph paragraph : xwpfDocument.getParagraphs()) {
        ParsedRowData parsedRowData = parseDocumentDataRow(paragraph.getText());
        try {
          data.setMappedValue(parsedRowData.getFieldName(), parsedRowData.getFieldValue());
        } catch (ValidationException e) {
          LOG.error("", e);
        }
      }

      dataAggregator.collectDataInExcelFile(data);
    } catch (IOException e) {
      throw new FileProcessorException("Error during open file.", e);
    }
  }

  private ParsedRowData parseDocumentDataRow(String rowData) {
    ParsedRowData result = new ParsedRowData();

    result.setFieldName(rowData.substring(0, rowData.indexOf(":")));
    result.setFieldValue(rowData.substring(rowData.indexOf(": \"") + 3, rowData.lastIndexOf("\"")));

    return result;
  }

  public void getFileFeedEventHandler(FileFeedEvent e) {
    inputFileName = e.getFileName();
    try {
      process();
    } catch (FileProcessorException ex) {
      LOG.error("Error during file processing.", ex);
    }
  }
}
