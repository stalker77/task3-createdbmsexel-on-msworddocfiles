package ru.tander.tasks.task3.dataaggregator;

import ru.tander.tasks.task3.entity.WordFileData;

/**
 * @author Evgeny Dobrokvashin
 *         Created by Stalker on 30.09.2015.
 * @version 1.0 Sep 2015
 */
public interface DataAggregator {
  void collectDataInExcelFile(WordFileData data);

  void saveDataInExcelFile(String fileName);
}
