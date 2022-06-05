package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;

@Dao
public interface TrainingDayMuscleGroupCrossRefDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insert(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Update
  public void update(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  @Delete
  public void delete(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);
}
