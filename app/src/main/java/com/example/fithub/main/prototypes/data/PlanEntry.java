package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fithub.main.prototypes.data.converter.ExerciseDataConverter;

@Entity
public class PlanEntry {
  @PrimaryKey public int id;

  @TypeConverters(ExerciseDataConverter.class)
  public ExerciseData exerciseData;

  public String weight;
  public String repeats;
}
