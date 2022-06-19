package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/** The type Exercise data. */
@Entity(tableName = "exercise_data")
public class ExerciseData implements Serializable {
  /** The Exercise data id. */
  @PrimaryKey(autoGenerate = true)
  public int exerciseDataId;

  private String instruction;
  private String name;
  private String imageUrl;
  private String videoUrl;

  /**
   * Instantiates a new Exercise data.
   *
   * @param exerciseDataId the exercise data id
   * @param name the name
   * @param instruction the instruction
   * @param imageUrl the image url
   * @param videoUrl the video url
   */
  public ExerciseData(
      int exerciseDataId, String name, String instruction, String imageUrl, String videoUrl) {
    this.exerciseDataId = exerciseDataId;
    this.name = name;
    this.instruction = instruction;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
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
   * Gets instruction.
   *
   * @return the instruction
   */
  public String getInstruction() {
    return instruction;
  }

  /**
   * Sets instruction.
   *
   * @param instruction the instruction
   */
  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  /**
   * Gets image url.
   *
   * @return the image url
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Sets image url.
   *
   * @param imageUrl the image url
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Gets video url.
   *
   * @return the video url
   */
  public String getVideoUrl() {
    return videoUrl;
  }

  /**
   * Sets video url.
   *
   * @param videoUrl the video url
   */
  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }
}
