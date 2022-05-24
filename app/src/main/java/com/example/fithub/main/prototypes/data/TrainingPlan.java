package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TrainingPlan {
  @PrimaryKey private int trainingPlanId;

  public TrainingPlan(int trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }

  public int getTrainingPlanId() {
    return trainingPlanId;
  }

  public void setTrainingPlanId(int trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }
}
