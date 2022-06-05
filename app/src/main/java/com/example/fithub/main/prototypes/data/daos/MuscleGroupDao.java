package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.MuscleGroup;

@Dao
public interface MuscleGroupDao {

  @Insert
  void insert(MuscleGroup muscleGroup);

  @Delete
  void delete(MuscleGroup muscleGroup);

  @Update
  void update(MuscleGroup muscleGroup);
}
