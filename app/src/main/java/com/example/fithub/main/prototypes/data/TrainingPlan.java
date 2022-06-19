package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** The type Training plan. */
@Entity(tableName = "training_plan")
public class TrainingPlan {
  @PrimaryKey(autoGenerate = true)
  private int trainingPlanId;

  private String name;

  private String notice = "";

  /**
   * Instantiates a new Training plan.
   *
   * @param trainingPlanId the training plan id
   * @param name the name
   */
  public TrainingPlan(int trainingPlanId, String name) {
    this.trainingPlanId = trainingPlanId;
    this.name = name;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets training plan id.
   *
   * @return the training plan id
   */
  public int getTrainingPlanId() {
    return trainingPlanId;
  }

  /**
   * Sets training plan id.
   *
   * @param trainingPlanId the training plan id
   */
  public void setTrainingPlanId(int trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }

  /**
   * Gets notice.
   *
   * @return the notice
   */
  public String getNotice() {
    return notice;
  }

  /**
   * Sets notice.
   *
   * @param notice the notice
   */
  public void setNotice(String notice) {
    this.notice = notice;
  }
}
