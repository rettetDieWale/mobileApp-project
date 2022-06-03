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

  /**
   * Add experience to total exp calculating new exp when a level up is reached.
   *
   * @param experience to add
   */
  public void addExperience(int experience) {
    progress = progress + experience;

    while (progress >= MAX_EXPERIENCE) {
      progress = progress - MAX_EXPERIENCE;
      level++;
    }
  }

  /**
   * Just for test purpose, is never used in any case.
   *
   * @param experience to subtract
   */
  public void subtractExperience(int experience) {
    progress = progress - experience;

    while (progress <= 0) {
      progress = progress + MAX_EXPERIENCE;
      level--;
    }
  }
}
