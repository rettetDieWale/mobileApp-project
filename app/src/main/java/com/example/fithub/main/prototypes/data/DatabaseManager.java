package com.example.fithub.main.prototypes.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fithub.main.prototypes.Templates;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DatabaseManager {

  public static AppDatabase appDatabase;
  public static Serializer serializer;

  public static void initDatabase(Context context) {
    RoomDatabase.Builder appDatabaseBuilder =
        Room.databaseBuilder(context, AppDatabase.class, "fitHub-database");
    appDatabaseBuilder.allowMainThreadQueries();
    appDatabase = (AppDatabase) appDatabaseBuilder.build();
    serializer = new Serializer();
  }

  public static void clearDatabase() {
    AsyncTask.execute(
        new Runnable() {
          @Override
          public void run() {
            appDatabase.clearAllTables();
          }
        });
  }

  public static void addTemplates(Context context) {

    Templates templates = new Templates();
    List<ExerciseData> exerciseDataList = templates.createExerciseDataTemplates();

    Type listType = new TypeToken<List<ExerciseData>>() {}.getType();

    List<ExerciseData> exerciseDataTemplates =
        (List<ExerciseData>) serializer.deserialize(context, listType, Savefile.EXERCISE_SAVEFILE);

    // Templates need to be created if file is corrupted or not existent
    if (exerciseDataTemplates == null) {
      for (int i = 0; i < exerciseDataList.size(); i++) {
        appDatabase.exerciseDataDao().insert(exerciseDataList.get(i));
      }
    }
  }
}
