package com.example.fithub.main.prototypes;

import java.io.Serializable;

/** String data class for an exercise displayed in the exercise fragment */
public class ExerciseData implements Serializable {
  private String name;
  private String instruction;
  private String imageUrl;
  private String videoUrl;

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
