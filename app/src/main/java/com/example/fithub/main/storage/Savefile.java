package com.example.fithub.main.storage;

/** Enum class containing Fithub save files for serialized objects and structures. */
public enum Savefile {
  EXPERIENCE_BAR_SAVEFILE("experience_bar.json"),
  MUSCLE_GROUP_CHART_SAVEFILE("muscle_group_chart.json"),
  TEST_FILE1("Person1.json");

  private String file;

  Savefile(String file) {
    this.file = file;
  }

  @Override
  public String toString() {
    return file;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }
}
