package com.example.fithub.main.prototypes.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity(primaryKeys = {"date", "muscleGroupId"})
public class TrainingDayMuscleGroupCrossRef {
  @NonNull public Date date;
  public int muscleGroupId;
}
