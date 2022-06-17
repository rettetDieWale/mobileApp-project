package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "training_plan")
public class TrainingPlan {
  @PrimaryKey(autoGenerate = true)
  private int trainingPlanId;

  private String name;

  private String notice = "";

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

  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }
}
