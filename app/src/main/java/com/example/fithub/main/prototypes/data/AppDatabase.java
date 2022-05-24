package com.example.fithub.main.prototypes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fithub.main.prototypes.data.dao.ExerciseDataDao;
import com.example.fithub.main.prototypes.data.dao.TrainingPlanDao;

@Database(
    entities = {TrainingPlan.class, PlanEntry.class, ExerciseData.class},
    version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract TrainingPlanDao trainingPlanDao();

  public abstract ExerciseDataDao exerciseDataDao();
}
