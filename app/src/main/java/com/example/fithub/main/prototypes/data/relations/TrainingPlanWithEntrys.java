package com.example.fithub.main.prototypes.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.List;

public class TrainingPlanWithEntrys {
  @Embedded public TrainingPlan trainingPlan;

  @Relation(parentColumn = "trainingPlanId", entityColumn = "entryId")
  public List<PlanEntry> entryList;
}
