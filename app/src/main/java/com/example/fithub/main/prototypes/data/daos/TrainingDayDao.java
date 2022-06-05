package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.relations.TrainingDayWithMuscleGroups;

import java.util.Date;
import java.util.List;

@Dao
public interface TrainingDayDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(TrainingDay trainingDay);

  @Delete
  void delete(TrainingDay trainingDay);

  @Update()
  void update(TrainingDay trainingDay);

  @Query("SELECT * FROM TRAINING_DAY")
  List<TrainingDay> getAll();

  @Query("SELECT * FROM TRAINING_DAY WHERE date = :date")
  TrainingDay getByDate(Date date);

  @Transaction
  @Query("SELECT * FROM training_day")
  public List<TrainingDayWithMuscleGroups> getTrainingDaysWithMuscleGroups();

  @Transaction
  @Query("SELECT * FROM training_day WHERE date = :trainingDayDate")
  public List<TrainingDayWithMuscleGroups> getTrainingDaysWithMuscleGroupsByDate(
      Date trainingDayDate);

  @Transaction
  @Query("SELECT * FROM training_day WHERE date >= :now ORDER BY date ASC LIMIT 1")
  public TrainingDay getNextTrainingDay(Date now);

}
