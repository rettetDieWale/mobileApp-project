package com.example.fithub.main.prototypes.data.converter;

import androidx.room.TypeConverter;

import com.example.fithub.main.prototypes.data.PlanEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PlanEntryConverter {

  @TypeConverter
  public String fromGroupTaskMemberList(List<PlanEntry> planEntries) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<PlanEntry>>() {}.getType();
    return gson.toJson(planEntries, listType);
  }

  @TypeConverter
  public List<PlanEntry> toGroupTaskMemberList(String string) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<PlanEntry>>() {}.getType();
    return gson.fromJson(string, listType);
  }
}
