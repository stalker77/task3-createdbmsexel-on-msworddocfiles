/*
* This class...
*
* Created by Evgeny Dobrokvashin (aka Stalker) on 30.09.2015 
*
* Copyright (c) 2015 MegaFon, All Rights Reserved.
*/

package ru.tander.tasks.task3.entity;

import ru.tander.tasks.task3.exception.ValidationException;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 30.09.2015.
 * @version 1.0 Sep 2015
 */
public class WordFileData {
  public static final String ADDRESS_FLD_NAME = "АДРЕС";

  public static final String COMPNAME_FLD_NAME = "НАЗВАНИЕ КОМПАНИИ";

  public static final String PHONE_FLD_NAME = "ТЕЛЕФОН";

  public static final String EMPNAME_FLD_NAME = "ИМЯ СОТРУДНИКА";

  public static final String POSITION_FLD_NAME = "ДОЛЖНОСТЬ";

  public static final String CELLPHONE_FLD_NAME = "МОБ. ТЕЛЕФОН";

  private String address;

  private String companyName;

  private String phone;

  private String employeeName;

  private String position;

  private String cellPhone;

  public void setMappedValue(String fieldName, String value) throws ValidationException {
    String upperFieldName = fieldName.toUpperCase().trim();
    switch (upperFieldName) {
      case ADDRESS_FLD_NAME:
        address = value;
        break;
      case COMPNAME_FLD_NAME:
        companyName = value;
        break;
      case PHONE_FLD_NAME:
        phone = value;
        break;
      case EMPNAME_FLD_NAME:
        employeeName = value;
        break;
      case POSITION_FLD_NAME:
        position = value;
        break;
      case CELLPHONE_FLD_NAME:
        cellPhone = value;
        break;
      default:
        throw new ValidationException("Supplied field name \"" + fieldName + "\" is not supported.");
    }
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public void setCellPhone(String cellPhone) {
    this.cellPhone = cellPhone;
  }
}
