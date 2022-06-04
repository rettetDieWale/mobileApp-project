package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.Date;
import java.util.List;

@Dao
public interface TrainingDayDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(TrainingDay trainingDay);

  @Delete
  void delete(TrainingDay trainingDay);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(TrainingDay trainingDay);

  @Query("SELECT * FROM TRAINING_DAY")
  List<TrainingPlan> getAll();

  @Query("SELECT * FROM TRAINING_DAY WHERE trainingDayId = :trainingDayId")
  TrainingDay getById(int trainingDayId);

  @Query("SELECT * FROM TRAINING_DAY WHERE date = :date")
  TrainingDay getByDate(Date date);
}
