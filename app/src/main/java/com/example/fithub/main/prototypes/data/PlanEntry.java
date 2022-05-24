package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "trainingplan_entry",
    foreignKeys = {
      @ForeignKey(
          entity = ExerciseData.class,
          parentColumns = {"exerciseDataId"},
          childColumns = {"exerciseDataId"})
    })
public class PlanEntry {
  @PrimaryKey public int entryId;

  private String weight;
  private String repeats;

  private int exerciseDataId;

  public PlanEntry(int entryId, String weight, String repeats, int exerciseDataId) {
    this.entryId = entryId;
    this.weight = weight;
    this.repeats = repeats;
    this.exerciseDataId = exerciseDataId;
  }

  public int getExerciseDataId() {
    return exerciseDataId;
  }

  public void setExerciseDataId(int exerciseDataId) {
    this.exerciseDataId = exerciseDataId;
  }

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getRepeats() {
    return repeats;
  }

  public void setRepeats(String repeats) {
    this.repeats = repeats;
  }
}
