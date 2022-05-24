package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TrainingPlan {
  @PrimaryKey private int trainingPlanId;
  private String name;

  public TrainingPlan(int trainingPlanId, String name) {
    this.trainingPlanId = trainingPlanId;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTrainingPlanId() {
    return trainingPlanId;
  }

  public void setTrainingPlanId(int trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }
}
