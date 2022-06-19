package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

/** The type Training day. */
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

  /**
   * Instantiates a new Training day.
   *
   * @param date the date
   * @param trainingPlanId the training plan id
   * @param wellBeing the well being
   * @param isArchived the is archived
   */
  public TrainingDay(Date date, int trainingPlanId, int wellBeing, boolean isArchived) {
    this.date = date;
    this.trainingPlanId = trainingPlanId;
    this.wellBeing = wellBeing;
    this.isArchived = isArchived;
  }

  /**
   * Is archived boolean.
   *
   * @return the boolean
   */
  public boolean isArchived() {
    return isArchived;
  }

  /**
   * Sets archived.
   *
   * @param archived the archived
   */
  public void setArchived(boolean archived) {
    isArchived = archived;
  }

  /**
   * Gets well being.
   *
   * @return the well being
   */
  public int getWellBeing() {
    return wellBeing;
  }

  /**
   * Sets well being.
   *
   * @param wellBeing the well being
   */
  public void setWellBeing(int wellBeing) {
    this.wellBeing = wellBeing;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets date.
   *
   * @param date the date
   */
  public void setDate(Date date) {
    this.date = date;
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
}
