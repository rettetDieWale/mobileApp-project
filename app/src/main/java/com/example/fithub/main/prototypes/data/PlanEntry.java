package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fithub.main.prototypes.data.converter.ExerciseDataConverter;

@Entity
public class PlanEntry {
  @PrimaryKey public int id;

  @TypeConverters(ExerciseDataConverter.class)
  public ExerciseData exerciseData;

  public String weight;
  public String repeats;

  public PlanEntry(int id, ExerciseData exerciseData, String weight, String repeats) {
    this.id = id;
    this.exerciseData = exerciseData;
    this.weight = weight;
    this.repeats = repeats;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ExerciseData getExerciseData() {
    return exerciseData;
  }

  public void setExerciseData(ExerciseData exerciseData) {
    this.exerciseData = exerciseData;
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
