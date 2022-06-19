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

/** The interface Training day dao. */
@Dao
public interface TrainingDayDao {

  /**
   * Insert.
   *
   * @param trainingDay the training day
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(TrainingDay trainingDay);

  /**
   * Delete.
   *
   * @param trainingDay the training day
   */
  @Delete
  void delete(TrainingDay trainingDay);

  /**
   * Update.
   *
   * @param trainingDay the training day
   */
  @Update()
  void update(TrainingDay trainingDay);

  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM TRAINING_DAY")
  List<TrainingDay> getAll();

  /**
   * Gets by date.
   *
   * @param date the date
   * @return the by date
   */
  @Query("SELECT * FROM TRAINING_DAY WHERE date = :date")
  TrainingDay getByDate(Date date);

  /**
   * Delete by id.
   *
   * @param date the date
   */
  @Query("DELETE FROM TRAINING_DAY WHERE date = :date")
  void deleteById(Date date);

  /**
   * Gets training days with muscle groups.
   *
   * @return the training days with muscle groups
   */
  @Transaction
  @Query("SELECT * FROM training_day")
  public List<TrainingDayWithMuscleGroups> getTrainingDaysWithMuscleGroups();

  /**
   * Gets training days with muscle groups by date.
   *
   * @param trainingDayDate the training day date
   * @return the training days with muscle groups by date
   */
  @Transaction
  @Query("SELECT * FROM training_day WHERE date = :trainingDayDate")
  public List<TrainingDayWithMuscleGroups> getTrainingDaysWithMuscleGroupsByDate(
      Date trainingDayDate);

  /**
   * Gets next training day.
   *
   * @param now the now
   * @return the next training day
   */
  @Transaction
  @Query("SELECT * FROM training_day WHERE date >= :now ORDER BY date ASC LIMIT 1")
  public TrainingDay getNextTrainingDay(Date now);
}
