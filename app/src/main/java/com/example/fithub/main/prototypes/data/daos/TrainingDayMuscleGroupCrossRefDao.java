package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;

import java.util.Date;

@Dao
public interface TrainingDayMuscleGroupCrossRefDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insert(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Update
  public void update(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Delete
  public void delete(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Query(
      "SELECT COUNT (muscleGroupId) FROM TrainingDayMuscleGroupCrossRef WHERE muscleGroupId = :muscleGroupId")
  public int countByMuscleGroupId(int muscleGroupId);

  @Query(
          "SELECT COUNT (muscleGroupId) FROM TrainingDayMuscleGroupCrossRef WHERE muscleGroupId = :muscleGroupId " +
                  "AND date < :now AND date > :limit")
  public int countPastDays(int muscleGroupId, Date now, Date limit);
}
