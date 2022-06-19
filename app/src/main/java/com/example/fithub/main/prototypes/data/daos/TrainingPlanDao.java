package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.List;

/** The interface Training plan dao. */
@Dao
public interface TrainingPlanDao {

  /**
   * Insert.
   *
   * @param trainingPlan the training plan
   */
  @Insert
  void insert(TrainingPlan trainingPlan);

  /**
   * Delete.
   *
   * @param trainingPlan the training plan
   */
  @Delete
  void delete(TrainingPlan trainingPlan);

  /**
   * Update.
   *
   * @param trainingPlan the training plan
   */
  @Update
  void update(TrainingPlan trainingPlan);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM TRAINING_PLAN")
  List<TrainingPlan> getAll();

  /**
   * Gets by id.
   *
   * @param trainingPlanId the training plan id
   * @return the by id
   */
  @Query("SELECT * FROM TRAINING_PLAN WHERE trainingPlanId = :trainingPlanId")
  TrainingPlan getById(int trainingPlanId);
}
