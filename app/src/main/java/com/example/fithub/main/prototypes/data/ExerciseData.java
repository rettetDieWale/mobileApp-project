package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "exercise_data")
public class ExerciseData implements Serializable {
  @PrimaryKey public int exerciseDataId;
  public String name;
  public String instruction;
  public String imageUrl;
  public String videoUrl;

  public ExerciseData(
      int exerciseDataId, String name, String instruction, String imageUrl, String videoUrl) {
    this.exerciseDataId = exerciseDataId;
    this.name = name;
    this.instruction = instruction;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
  }

  public int getExerciseDataId() {
    return exerciseDataId;
  }

  public void setExerciseDataId(int exerciseDataId) {
    this.exerciseDataId = exerciseDataId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }
}
