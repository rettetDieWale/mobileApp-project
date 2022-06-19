package com.example.fithub.main.prototypes.data.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;

import java.util.List;

/** The type Training day with muscle groups. */
public class TrainingDayWithMuscleGroups {
  /** The Training day. */
  @Embedded public TrainingDay trainingDay;

  /** The Muscle group list. */
  @Relation(
      parentColumn = "date",
      entityColumn = "muscleGroupId",
      associateBy = @Junction(TrainingDayMuscleGroupCrossRef.class))
  public List<MuscleGroup> muscleGroupList;
}
