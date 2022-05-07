package com.example.fithub.main.prototypes;

/** Class for storing user experience data. */
public class ExperienceBar {

  private int max;
  private int progress;

  public ExperienceBar(int max, int progress) {
    this.max = max;
    this.progress = progress;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public int getProgress() {
    return progress;
  }

  public void setProgress(int progress) {
    this.progress = progress;
  }
}
