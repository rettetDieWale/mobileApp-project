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

  @PrimaryKey(autoGenerate = true)
  private int trainingDayId;

  private int trainingPlanId;

  private Date date;

  private int wellBeing;

  public TrainingDay(int trainingDayId, Date date, int trainingPlanId, int wellBeing) {
    this.trainingDayId = trainingDayId;
    this.date = date;
    this.trainingPlanId = trainingPlanId;
    this.wellBeing = wellBeing;
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

  public int getTrainingDayId() {
    return trainingDayId;
  }

  public void setTrainingDayId(int trainingDayId) {
    this.trainingDayId = trainingDayId;
  }
}
