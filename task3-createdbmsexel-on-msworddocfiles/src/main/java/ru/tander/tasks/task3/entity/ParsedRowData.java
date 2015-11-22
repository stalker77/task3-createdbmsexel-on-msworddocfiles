/*
* This class realize ...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 30.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.entity;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 30.09.2015.
 * @version 1.0 Sep 2015
 */
public class ParsedRowData {
  private String fieldName;

  private String fieldValue;

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldValue() {
    return fieldValue;
  }

  public void setFieldValue(String fieldValue) {
    this.fieldValue = fieldValue;
  }
}
