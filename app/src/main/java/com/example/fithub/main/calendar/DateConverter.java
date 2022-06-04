package com.example.fithub.main.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

  static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  /**
   * Convert a string into a date object with the format: dd-MM-yyyy
   *
   * @param dateString
   * @return date
   */
  public static Date parseStringToDate(final String dateString) {
    Date date = null;
    try {

      date = dateFormat.parse(dateString);
    } catch (ParseException parseException) {

    }
    return date;
  }

  public static String parseDateToString(final Date date) {
    String stringDate = dateFormat.format(date);
    return stringDate;
  }
}
