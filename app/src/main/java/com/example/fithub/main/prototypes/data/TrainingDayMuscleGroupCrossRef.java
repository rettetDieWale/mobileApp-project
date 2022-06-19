package com.example.fithub.main.prototypes.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

/** The type Training day muscle group cross ref. */
@Entity(primaryKeys = {"date", "muscleGroupId"})
public class TrainingDayMuscleGroupCrossRef {

  /** The Date. */
  @NonNull public Date date;
  /** The Muscle group id. */
  public int muscleGroupId;

  /**
   * Instantiates a new Training day muscle group cross ref.
   *
   * @param date the date
   * @param muscleGroupId the muscle group id
   */
  public TrainingDayMuscleGroupCrossRef(@NonNull Date date, int muscleGroupId) {
    this.date = date;
    this.muscleGroupId = muscleGroupId;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  @NonNull
  public Date getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(@NonNull Date date) {
    this.date = date;
  }

  /**
   * Gets muscle group id.
   *
   * @return the muscle group id
   */
  public int getMuscleGroupId() {
    return muscleGroupId;
  }

  /**
   * Sets muscle group id.
   *
   * @param muscleGroupId the muscle group id
   */
  public void setMuscleGroupId(int muscleGroupId) {
    this.muscleGroupId = muscleGroupId;
  }
}
