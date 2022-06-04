package com.example.fithub.main.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

  /**
   * Convert a string into a date object with the format: dd-MM-yyyy
   *
   * @param dateString
   * @return date
   */
  public static Date parseDateString(final String dateString) {
    Date date = null;
    try {
      final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      date = dateFormat.parse(dateString);
    } catch (ParseException parseException) {

    }
    return date;
  }
}
