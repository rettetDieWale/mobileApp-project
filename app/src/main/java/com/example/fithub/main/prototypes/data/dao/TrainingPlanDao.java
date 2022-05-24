package com.example.fithub.main.prototypes.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.List;

@Dao
public interface TrainingPlanDao {
  @Query("SELECT * FROM TrainingPlan")
  List<TrainingPlan> getAll();

  @Insert
  void insertAll(TrainingPlan... trainingPlans);

  @Delete
  void delete(TrainingPlan trainingPlan);
}
