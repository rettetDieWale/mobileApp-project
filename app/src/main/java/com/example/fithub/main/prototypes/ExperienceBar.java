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

  /**
   * Instantiates a new Experience bar.
   *
   * @param max the max
   * @param progress the progress
   * @param level the level
   */
  public ExperienceBar(final int max, final int progress, final int level) {
    this.max = max;
    this.progress = progress;
    this.level = level;
  }

  /**
   * Gets max.
   *
   * @return the max
   */
  public int getMax() {
    return max;
  }

  /**
   * Sets max.
   *
   * @param max the max
   */
  public void setMax(int max) {
    this.max = max;
  }

  /**
   * Gets progress.
   *
   * @return the progress
   */
  public int getProgress() {
    return progress;
  }

  /**
   * Sets progress.
   *
   * @param progress the progress
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * Gets level.
   *
   * @return the level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Sets level.
   *
   * @param level the level
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Gets max experience.
   *
   * @return the max experience
   */
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
