package com.example.fithub.main.prototypes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.fithub.main.Converters;
import com.example.fithub.main.prototypes.data.daos.ExerciseDataDao;
import com.example.fithub.main.prototypes.data.daos.PlanEntryDao;
import com.example.fithub.main.prototypes.data.daos.TrainingDayDao;
import com.example.fithub.main.prototypes.data.daos.TrainingPlanDao;

/** Singleton database object for application. */
@Database(
    entities = {TrainingPlan.class, PlanEntry.class, ExerciseData.class, TrainingDay.class},
    version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
  public abstract TrainingPlanDao trainingPlanDao();

  public abstract ExerciseDataDao exerciseDataDao();

  public abstract PlanEntryDao planEntryDao();

  public abstract TrainingDayDao trainingDayDao();
}
