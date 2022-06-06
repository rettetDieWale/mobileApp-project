package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
    tableName = "training_day",
    foreignKeys = {
      @ForeignKey(
          entity = TrainingPlan.class,
          parentColumns = {"trainingPlanId"},
          childColumns = {"trainingPlanId"})
    })
public class TrainingDay {

  @PrimaryKey private Date date;

  private int trainingPlanId;

  private int wellBeing;

  private boolean isArchived;

  public TrainingDay(Date date, int trainingPlanId, int wellBeing, boolean isArchived) {
    this.date = date;
    this.trainingPlanId = trainingPlanId;
    this.wellBeing = wellBeing;
    this.isArchived = isArchived;
  }

  public boolean isArchived() {
    return isArchived;
  }

  public void setArchived(boolean archived) {
    isArchived = archived;
  }

  public int getWellBeing() {
    return wellBeing;
  }

  public void setWellBeing(int wellBeing) {
    this.wellBeing = wellBeing;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getTrainingPlanId() {
    return trainingPlanId;
  }

  public void setTrainingPlanId(int trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }
}
