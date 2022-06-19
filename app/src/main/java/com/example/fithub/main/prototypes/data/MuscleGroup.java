package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** The type Muscle group. */
@Entity
public class MuscleGroup {
  /** The Muscle group id. */
  @PrimaryKey public int muscleGroupId;
  /** The Muscle group name. */
  public String muscleGroupName;

  /**
   * Instantiates a new Muscle group.
   *
   * @param muscleGroupId the muscle group id
   * @param muscleGroupName the muscle group name
   */
  public MuscleGroup(int muscleGroupId, String muscleGroupName) {
    this.muscleGroupId = muscleGroupId;
    this.muscleGroupName = muscleGroupName;
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

  /**
   * Gets muscle group name.
   *
   * @return the muscle group name
   */
  public String getMuscleGroupName() {
    return muscleGroupName;
  }

  /**
   * Sets muscle group name.
   *
   * @param muscleGroupName the muscle group name
   */
  public void setMuscleGroupName(String muscleGroupName) {
    this.muscleGroupName = muscleGroupName;
  }
}
