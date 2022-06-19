package com.example.fithub.main.prototypes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.fithub.main.prototypes.data.daos.ExerciseDataDao;
import com.example.fithub.main.prototypes.data.daos.MuscleGroupDao;
import com.example.fithub.main.prototypes.data.daos.PlanEntryDao;
import com.example.fithub.main.prototypes.data.daos.TrainingDayDao;
import com.example.fithub.main.prototypes.data.daos.TrainingDayMuscleGroupCrossRefDao;
import com.example.fithub.main.prototypes.data.daos.TrainingPlanDao;

/** Singleton database object for application. */
@Database(
    entities = {
      TrainingPlan.class,
      PlanEntry.class,
      ExerciseData.class,
      TrainingDay.class,
      MuscleGroup.class,
      TrainingDayMuscleGroupCrossRef.class
    },
    version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
  /**
   * Training plan dao training plan dao.
   *
   * @return the training plan dao
   */
  public abstract TrainingPlanDao trainingPlanDao();

  /**
   * Exercise data dao exercise data dao.
   *
   * @return the exercise data dao
   */
  public abstract ExerciseDataDao exerciseDataDao();

  /**
   * Plan entry dao plan entry dao.
   *
   * @return the plan entry dao
   */
  public abstract PlanEntryDao planEntryDao();

  /**
   * Training day dao training day dao.
   *
   * @return the training day dao
   */
  public abstract TrainingDayDao trainingDayDao();

  /**
   * Muscle group dao muscle group dao.
   *
   * @return the muscle group dao
   */
  public abstract MuscleGroupDao muscleGroupDao();

  /**
   * Training day muscle group cross ref dao training day muscle group cross ref dao.
   *
   * @return the training day muscle group cross ref dao
   */
  public abstract TrainingDayMuscleGroupCrossRefDao trainingDayMuscleGroupCrossRefDao();
}
