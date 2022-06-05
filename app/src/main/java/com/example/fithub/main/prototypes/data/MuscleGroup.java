package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MuscleGroup {
  @PrimaryKey public int muscleGroupId;
  public String muscleGroupName;

  public MuscleGroup(int muscleGroupId, String muscleGroupName) {
    this.muscleGroupId = muscleGroupId;
    this.muscleGroupName = muscleGroupName;
  }

  public int getMuscleGroupId() {
    return muscleGroupId;
  }

  public void setMuscleGroupId(int muscleGroupId) {
    this.muscleGroupId = muscleGroupId;
  }

  public String getMuscleGroupName() {
    return muscleGroupName;
  }

  public void setMuscleGroupName(String muscleGroupName) {
    this.muscleGroupName = muscleGroupName;
  }
}
