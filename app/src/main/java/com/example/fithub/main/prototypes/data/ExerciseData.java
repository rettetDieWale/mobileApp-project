package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExerciseData {
  @PrimaryKey public int id;

  public String name;
  public String instruction;
  public String imageUrl;
  public String videoUrl;
}
