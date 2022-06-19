package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/** The type Plan entry. */
@Entity(
    tableName = "trainingplan_entry",
    foreignKeys = {
      @ForeignKey(
          entity = ExerciseData.class,
          parentColumns = {"exerciseDataId"},
          childColumns = {"exerciseDataId"})
    })
public class PlanEntry {
  /** The Entry id. */
  @PrimaryKey(autoGenerate = true)
  public int entryId;

  private String weight;
  private String repeats;

  private int exerciseDataId;
  private int trainingPlanId;

  /**
   * Instantiates a new Plan entry.
   *
   * @param entryId the entry id
   * @param weight the weight
   * @param repeats the repeats
   * @param exerciseDataId the exercise data id
   * @param trainingPlanId the training plan id
   */
  public PlanEntry(
      int entryId, String weight, String repeats, int exerciseDataId, int trainingPlanId) {
    this.entryId = entryId;
    this.weight = weight;
    this.repeats = repeats;
    this.exerciseDataId = exerciseDataId;
    this.trainingPlanId = trainingPlanId;
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
   * Gets exercise data id.
   *
   * @return the exercise data id
   */
  public int getExerciseDataId() {
    return exerciseDataId;
  }

  /**
   * Sets exercise data id.
   *
   * @param exerciseDataId the exercise data id
   */
  public void setExerciseDataId(int exerciseDataId) {
    this.exerciseDataId = exerciseDataId;
  }

  /**
   * Gets entry id.
   *
   * @return the entry id
   */
  public int getEntryId() {
    return entryId;
  }

  /**
   * Sets entry id.
   *
   * @param entryId the entry id
   */
  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }

  /**
   * Gets weight.
   *
   * @return the weight
   */
  public String getWeight() {
    return weight;
  }

  /**
   * Sets weight.
   *
   * @param weight the weight
   */
  public void setWeight(String weight) {
    this.weight = weight;
  }

  /**
   * Gets repeats.
   *
   * @return the repeats
   */
  public String getRepeats() {
    return repeats;
  }

  /**
   * Sets repeats.
   *
   * @param repeats the repeats
   */
  public void setRepeats(String repeats) {
    this.repeats = repeats;
  }
}
