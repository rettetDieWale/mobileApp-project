package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.ExerciseData;

import java.util.List;

@Dao
public interface ExerciseDataDao {

  @Query("SELECT * FROM EXERCISE_DATA WHERE exerciseDataId = :exerciseDataId ")
  ExerciseData getExerciseData(int exerciseDataId);

  @Query("SELECT * FROM EXERCISE_DATA")
  List<ExerciseData> getAll();

  @Insert
  void insert(ExerciseData exerciseData);

  @Update(entity = ExerciseData.class)
  void update(ExerciseData targetExerciseData);

  @Delete
  void delete(ExerciseData exerciseData);
}
