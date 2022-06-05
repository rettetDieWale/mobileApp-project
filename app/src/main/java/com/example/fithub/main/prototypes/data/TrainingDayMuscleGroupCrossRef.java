package com.example.fithub.main.prototypes.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity(primaryKeys = {"date", "muscleGroupId"})
public class TrainingDayMuscleGroupCrossRef {

  @NonNull public Date date;
  public int muscleGroupId;

  public TrainingDayMuscleGroupCrossRef(@NonNull Date date, int muscleGroupId) {
    this.date = date;
    this.muscleGroupId = muscleGroupId;
  }

  @NonNull
  public Date getDate() {
    return date;
  }

  public void setDate(@NonNull Date date) {
    this.date = date;
  }

  public int getMuscleGroupId() {
    return muscleGroupId;
  }

  public void setMuscleGroupId(int muscleGroupId) {
    this.muscleGroupId = muscleGroupId;
  }
}
