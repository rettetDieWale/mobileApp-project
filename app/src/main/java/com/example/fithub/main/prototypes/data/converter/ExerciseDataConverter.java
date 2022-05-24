package com.example.fithub.main.prototypes.data.converter;

import androidx.room.TypeConverter;

import com.example.fithub.main.prototypes.data.ExerciseData;
import com.google.gson.Gson;

public class ExerciseDataConverter {
  @TypeConverter
  public String fromGroupTaskMemberList(ExerciseData exerciseData) {
    Gson gson = new Gson();
    return gson.toJson(exerciseData, exerciseData.getClass());
  }

  @TypeConverter
  public ExerciseData toGroupTaskMemberList(String string) {
    Gson gson = new Gson();
    return gson.fromJson(string, ExerciseData.class);
  }
}
