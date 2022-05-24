package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fithub.main.prototypes.data.converter.PlanEntryConverter;

import java.util.List;

@Entity
public class TrainingPlan {
  @PrimaryKey public int id;

  @TypeConverters(PlanEntryConverter.class)
  public List<PlanEntry> entries;
}
