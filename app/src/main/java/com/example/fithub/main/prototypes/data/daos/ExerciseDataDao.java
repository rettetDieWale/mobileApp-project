package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.ExerciseData;

import java.util.List;

/** The interface Exercise data dao. */
@Dao
public interface ExerciseDataDao {

  /**
   * Gets exercise data.
   *
   * @param exerciseDataId the exercise data id
   * @return the exercise data
   */
  @Query("SELECT * FROM EXERCISE_DATA WHERE exerciseDataId = :exerciseDataId ")
  ExerciseData getExerciseData(int exerciseDataId);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM EXERCISE_DATA")
  List<ExerciseData> getAll();

  /**
   * Insert.
   *
   * @param exerciseData the exercise data
   */
  @Insert
  void insert(ExerciseData exerciseData);

  /**
   * Update.
   *
   * @param targetExerciseData the target exercise data
   */
  @Update(entity = ExerciseData.class)
  void update(ExerciseData targetExerciseData);

  /**
   * Delete.
   *
   * @param exerciseData the exercise data
   */
  @Delete
  void delete(ExerciseData exerciseData);
}
