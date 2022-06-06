package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.MuscleGroup;

import java.util.List;

@Dao
public interface MuscleGroupDao {

  @Insert
  void insert(MuscleGroup muscleGroup);

  @Delete
  void delete(MuscleGroup muscleGroup);

  @Update
  void update(MuscleGroup muscleGroup);

  @Query("SELECT * FROM musclegroup")
  public List<MuscleGroup> getAll();

  @Query("SELECT * FROM musclegroup WHERE muscleGroupId = :id")
  public MuscleGroup getById(int id);
}
