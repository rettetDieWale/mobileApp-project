package com.example.fithub.main.prototypes.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class DatabaseManager {

  public static AppDatabase appDatabase;

  public static void initDatabase(Context context) {
    appDatabase = Room.databaseBuilder(context, AppDatabase.class, "fitHub-database").build();
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

  public void test() {
    AsyncTask.execute(
        new Runnable() {
          @Override
          public void run() {
            com.example.fithub.main.prototypes.data.ExerciseData exerciseData2 =
                new com.example.fithub.main.prototypes.data.ExerciseData(
                    1,
                    "Klimmzug",
                    "Klimmzüge Instruktionen hier einfügen ...",
                    "https://www.uebungen.ws/wp-content/uploads/2011/07/klimmzuege.jpg",
                    "https://youtu.be/T78xCiw_R6g");

            appDatabase.exerciseDataDao().insert(exerciseData2);
          }
        });
  }
}
