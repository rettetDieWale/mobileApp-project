package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.MuscleGroup;

import java.util.List;

/** The interface Muscle group dao. */
@Dao
public interface MuscleGroupDao {

  /**
   * Insert.
   *
   * @param muscleGroup the muscle group
   */
  @Insert
  void insert(MuscleGroup muscleGroup);

  /**
   * Delete.
   *
   * @param muscleGroup the muscle group
   */
  @Delete
  void delete(MuscleGroup muscleGroup);

  /**
   * Update.
   *
   * @param muscleGroup the muscle group
   */
  @Update
  void update(MuscleGroup muscleGroup);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM musclegroup")
  public List<MuscleGroup> getAll();

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @Query("SELECT * FROM musclegroup WHERE muscleGroupId = :id")
  public MuscleGroup getById(int id);
}
