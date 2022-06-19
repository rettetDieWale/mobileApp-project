package com.example.fithub.main.prototypes.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fithub.main.prototypes.data.PlanEntry;

import java.util.List;

/** The interface Plan entry dao. */
@Dao
public interface PlanEntryDao {
  /**
   * Gets all.
   *
   * @return the all
   */
  @Query("SELECT * FROM TRAININGPLAN_ENTRY")
  List<PlanEntry> getAll();

  /**
   * Gets plan entry list by plan id.
   *
   * @param trainingPlanId the training plan id
   * @return the plan entry list by plan id
   */
  @Query("SELECT * FROM TRAININGPLAN_ENTRY WHERE trainingPlanId = :trainingPlanId")
  List<PlanEntry> getPlanEntryListByPlanId(int trainingPlanId);

  /**
   * Gets plan entry by id.
   *
   * @param entryId the entry id
   * @return the plan entry by id
   */
  @Query("SELECT * FROM TRAININGPLAN_ENTRY WHERE entryId = :entryId")
  PlanEntry getPlanEntryById(int entryId);

  /**
   * Gets count by plan id.
   *
   * @param planId the plan id
   * @return the count by plan id
   */
  @Query("SELECT COUNT(trainingPlanId) FROM TRAININGPLAN_ENTRY WHERE trainingPlanId = :planId")
  int getCountByPlanId(int planId);

  /**
   * Insert.
   *
   * @param planEntry the plan entry
   */
  @Insert
  void insert(PlanEntry planEntry);

  /**
   * Update.
   *
   * @param planEntry the plan entry
   */
  @Update
  void update(PlanEntry planEntry);

  /**
   * Delete.
   *
   * @param planEntry the plan entry
   */
  @Delete
  void delete(PlanEntry planEntry);
}
