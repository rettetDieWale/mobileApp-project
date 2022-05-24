package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.fithub.main.prototypes.data.TrainingPlan;
import com.example.fithub.main.prototypes.data.relations.TrainingPlanWithEntrys;

import java.util.List;

@Dao
public interface TrainingPlanDao {

  @Insert
  void insert(TrainingPlan trainingPlan);

  @Delete
  void delete(TrainingPlan trainingPlan);

  @Transaction
  @Query("SELECT * FROM TrainingPlan")
  public List<TrainingPlanWithEntrys> getTrainingPlansWithEntrys();
}
