package com.example.fithub.main.prototypes.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fithub.main.prototypes.Templates;
import com.example.fithub.main.storage.Serializer;

import java.util.List;

/** Access API for database object over the whole app. */
public class DatabaseManager {

  /** The constant appDatabase. */
  public static AppDatabase appDatabase;
  /** The constant serializer. */
  public static Serializer serializer;

  /**
   * Initializes singleton database object.
   *
   * @param context of application
   */
  public static void initDatabase(final Context context) {
    final RoomDatabase.Builder appDatabaseBuilder =
        Room.databaseBuilder(context, AppDatabase.class, "fitHub-database");
    appDatabaseBuilder.allowMainThreadQueries();
    appDatabase = (AppDatabase) appDatabaseBuilder.build();
    serializer = new Serializer();
  }

  /** remove all tables from database. */
  public static void clearDatabase() {
    AsyncTask.execute(
        new Runnable() {
          @Override
          public void run() {
            appDatabase.clearAllTables();
          }
        });
  }

  /**
   * load templates into database.
   *
   * @param context of Application
   */
  public static void addTemplates(Context context) {

    appDatabase.clearAllTables();

    final Templates templates = new Templates();
    final List<PlanEntry> planEntryList = templates.createPlanEntryTemplates();

    final List<ExerciseData> exerciseDataList = templates.createExerciseDataTemplates();
    for (int i = 0; i < exerciseDataList.size(); i++) {
      appDatabase.exerciseDataDao().insert(exerciseDataList.get(i));
    }

    for (int i = 0; i < planEntryList.size(); i++) {
      appDatabase.planEntryDao().insert(planEntryList.get(i));
    }

    final List<TrainingPlan> trainingPlanList = templates.createTrainingPlanTemplates();

    for (int i = 0; i < trainingPlanList.size(); i++) {
      appDatabase.trainingPlanDao().insert(trainingPlanList.get(i));
    }

    templates.addMuscleGroups();
  }
}
