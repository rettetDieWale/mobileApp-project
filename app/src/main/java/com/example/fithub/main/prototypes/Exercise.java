package com.example.fithub.main.prototypes;

import com.example.fithub.main.prototypes.data.ExerciseData;

/** Class for the exercise type displayed in the training plan. */
public class Exercise {
  private String name;
  private String weight;
  private String repeats;
  private ExerciseData exerciseData;

  public Exercise(String name, String weight, String repeats, ExerciseData exerciseData) {
    this.name = name;
    this.weight = weight;
    this.repeats = repeats;
    this.exerciseData = exerciseData;
    this.exerciseData.setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    this.exerciseData.setName(name);
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

  public ExerciseData getExerciseData() {
    return exerciseData;
  }

  public void setExerciseData(ExerciseData exerciseData) {
    this.exerciseData = exerciseData;
  }
}
