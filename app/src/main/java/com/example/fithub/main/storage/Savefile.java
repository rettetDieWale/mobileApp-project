package com.example.fithub.main.storage;

/** Enum class containing Fithub save files for serialized objects and structures. */
public enum Savefile {
  /** Experience bar savefile savefile. */
  EXPERIENCE_BAR_SAVEFILE("experience_bar.json"),
  /** Muscle group chart savefile savefile. */
  MUSCLE_GROUP_CHART_SAVEFILE("muscle_group_chart.json"),
  /** Test file 1 savefile. */
  TEST_FILE1("Person1.json");

  private String file;

  Savefile(String file) {
    this.file = file;
  }

  @Override
  public String toString() {
    return file;
  }

  /**
   * Gets file.
   *
   * @return the file
   */
  public String getFile() {
    return file;
  }

  /**
   * Sets file.
   *
   * @param file the file
   */
  public void setFile(String file) {
    this.file = file;
  }
}
