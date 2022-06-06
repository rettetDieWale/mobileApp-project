package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;

import java.util.Date;
import java.util.List;

@Dao
public interface TrainingDayMuscleGroupCrossRefDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insert(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Update
  public void update(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Delete
  public void delete(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Query("SELECT * from TrainingDayMuscleGroupCrossRef WHERE date = :date")
  public List<TrainingDayMuscleGroupCrossRef> getByDate(Date date);

  @Query(
      "SELECT COUNT (muscleGroupId) FROM TrainingDayMuscleGroupCrossRef WHERE muscleGroupId = :muscleGroupId")
  public int countByMuscleGroupId(int muscleGroupId);

  @Query(
      "SELECT * FROM TrainingDayMuscleGroupCrossRef WHERE date BETWEEN :lowerDate AND :upperDate")
  public List<TrainingDayMuscleGroupCrossRef> getInterval(Date lowerDate, Date upperDate);

  @Query("DELETE FROM TrainingDayMuscleGroupCrossRef WHERE date = :date")
  public void deleteAllByDate(Date date);
}
