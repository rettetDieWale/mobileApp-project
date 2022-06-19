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

/** The interface Training day muscle group cross ref dao. */
@Dao
public interface TrainingDayMuscleGroupCrossRefDao {

  /**
   * Insert.
   *
   * @param trainingDayMuscleGroupCrossRef the training day muscle group cross ref
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insert(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  /**
   * Update.
   *
   * @param trainingDayMuscleGroupCrossRef the training day muscle group cross ref
   */
  @Update
  public void update(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  /**
   * Delete.
   *
   * @param trainingDayMuscleGroupCrossRef the training day muscle group cross ref
   */
  @Delete
  public void delete(TrainingDayMuscleGroupCrossRef trainingDayMuscleGroupCrossRef);

  /**
   * Gets by date.
   *
   * @param date the date
   * @return the by date
   */
  @Query("SELECT * from TrainingDayMuscleGroupCrossRef WHERE date = :date")
  public List<TrainingDayMuscleGroupCrossRef> getByDate(Date date);

  /**
   * Count by muscle group id int.
   *
   * @param muscleGroupId the muscle group id
   * @return the int
   */
  @Query(
      "SELECT COUNT (muscleGroupId) FROM TrainingDayMuscleGroupCrossRef WHERE muscleGroupId = :muscleGroupId")
  public int countByMuscleGroupId(int muscleGroupId);

  /**
   * Count past days int.
   *
   * @param muscleGroupId the muscle group id
   * @param now the now
   * @param limit the limit
   * @return the int
   */
  @Query(
      "SELECT COUNT (muscleGroupId) FROM TrainingDayMuscleGroupCrossRef WHERE muscleGroupId = :muscleGroupId "
          + "AND date < :now AND date > :limit")
  public int countPastDays(int muscleGroupId, Date now, Date limit);

  /**
   * Gets interval.
   *
   * @param lowerDate the lower date
   * @param upperDate the upper date
   * @return the interval
   */
  @Query(
      "SELECT * FROM TrainingDayMuscleGroupCrossRef WHERE date BETWEEN :lowerDate AND :upperDate")
  public List<TrainingDayMuscleGroupCrossRef> getInterval(Date lowerDate, Date upperDate);

  /**
   * Delete all by date.
   *
   * @param date the date
   */
  @Query("DELETE FROM TrainingDayMuscleGroupCrossRef WHERE date = :date")
  public void deleteAllByDate(Date date);
}
