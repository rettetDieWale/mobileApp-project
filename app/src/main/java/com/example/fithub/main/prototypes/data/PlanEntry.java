package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlanEntry {
  @PrimaryKey public int id;

  public ExerciseData exerciseData;
  public String weight;
  public String repeats;
}
