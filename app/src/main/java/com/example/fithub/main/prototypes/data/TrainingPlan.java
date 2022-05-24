package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class TrainingPlan {
  @PrimaryKey public int id;
  public List<PlanEntry> entrys;
}
