package com.example.fithub.main.prototypes;

/** Class for storing user experience data. */
public class ExperienceBar {

  /** Value to reach one level up * */
  private int MAX_EXPERIENCE = 100;
  /** Max experience for reaching one level. */
  private int max;
  /** Current progress xp wise as raw integer. */
  private int progress;
  /** Current user level * */
  private int level;

  public ExperienceBar(int max, int progress, int level) {
    this.max = max;
    this.progress = progress;
    this.level = level;
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

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getMAX_EXPERIENCE() {
    return MAX_EXPERIENCE;
  }

  public void addExperience(int experience) {
    progress = progress + experience;

    while (progress >= 100) {
      progress = progress - 100;
      level++;
    }
  }
}
