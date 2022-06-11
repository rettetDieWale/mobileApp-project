package com.example.fithub.main.prototypes.data;

import androidx.room.TypeConverter;

import java.util.Date;

/** Converters needed to store java objects data into sqlite database. */
public class Converters {
  @TypeConverter
  public static Date fromTimestamp(Long value) {
    return value == null ? null : new Date(value);
  }

  @TypeConverter
  public static Long dateToTimestamp(Date date) {
    return date == null ? null : date.getTime();
  }
}
