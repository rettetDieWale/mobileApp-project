package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.List;

@Dao
public interface TrainingPlanDao {

  @Insert
  void insert(TrainingPlan trainingPlan);

  @Delete
  void delete(TrainingPlan trainingPlan);

  @Query("SELECT * FROM TRAINING_PLAN")
  List<TrainingPlan> getAll();
}
