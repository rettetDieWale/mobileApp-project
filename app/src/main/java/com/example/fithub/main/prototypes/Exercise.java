package com.example.fithub.main.prototypes;

/** Class for the exercise type. */
public class Exercise {
  private String name;
  private int weight;
  private int repeats;
  private ExerciseData exerciseData;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getRepeats() {
    return repeats;
  }

  public void setRepeats(int repeats) {
    this.repeats = repeats;
  }

  public ExerciseData getExerciseData() {
    return exerciseData;
  }

  public void setExerciseData(ExerciseData exerciseData) {
    this.exerciseData = exerciseData;
  }
}
