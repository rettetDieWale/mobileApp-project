package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.PlanEntry;

import java.util.List;

@Dao
public interface PlanEntryDao {
  @Query("SELECT * FROM TRAININGPLAN_ENTRY")
  List<PlanEntry> getAll();

  @Query("SELECT * FROM TRAININGPLAN_ENTRY WHERE trainingPlanId = :trainingPlanId")
  List<PlanEntry> getPlanEntryListByPlanId(int trainingPlanId);

  @Query("SELECT * FROM TRAININGPLAN_ENTRY WHERE entryId = :entryId")
  PlanEntry getPlanEntryById(int entryId);

  @Insert
  void insert(PlanEntry planEntry);

  @Update
  void update(PlanEntry planEntry);

  @Delete
  void delete(PlanEntry planEntry);
}
