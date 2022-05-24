package com.example.fithub.main.prototypes.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.ExerciseData;

import java.util.List;

@Dao
public interface ExerciseDataDao {

  @Query("SELECT * FROM ExerciseData")
  List<ExerciseData> getAll();

  @Insert
  void insert(ExerciseData exerciseData);

  @Update
  void update(ExerciseData targetExerciseData);

  @Delete
  void delete(ExerciseData exerciseData);
}
