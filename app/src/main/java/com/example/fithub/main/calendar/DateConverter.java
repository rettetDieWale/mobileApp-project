package com.example.fithub.main.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter used for converting date objects into the needed string format and back all trough the
 * application on various points.
 */
public class DateConverter {

  static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  /**
   * Convert a string into a date object with the format: dd-mm-yyyy
   *
   * @param dateString
   * @return date as object.
   */
  public static Date parseStringToDate(final String dateString) {
    Date date = null;
    try {

      date = dateFormat.parse(dateString);
    } catch (ParseException parseException) {

    }
    return date;
  }

  /**
   * Convert a Date object back into string with the format: dd-mm-yyyy
   *
   * @param date object that should be converted
   * @return date string
   */
  public static String parseDateToString(final Date date) {
    final String stringDate = dateFormat.format(date);
    return stringDate;
  }
}
