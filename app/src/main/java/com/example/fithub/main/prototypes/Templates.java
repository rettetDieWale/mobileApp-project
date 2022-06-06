package com.example.fithub.main.prototypes;

import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.ExerciseData;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/** Helper class to create templates or reset exercises to factory settings. */
public class Templates {

  /**
   * Creates the exercise templates that are hardcoded.
   *
   * @return List with template exercises
   */
  public List<ExerciseData> createExerciseDataTemplates() {
    final List<ExerciseData> exerciseData = new ArrayList<>();

    final ExerciseData emptyExerciseDataTemplate =
        new ExerciseData(
            1,
            "Hier Übungsnamen eingeben...",
            "Hier Anleitung einfügen...",
            "Hier Bild URL einfügen...",
            "Hier Video Url einfügen...");
    exerciseData.add(emptyExerciseDataTemplate);

    final ExerciseData chinupsExerciseDataTemplate =
        new ExerciseData(
            2,
            "Klimmzug",
            "Wichtigste Grundregel beim Klimmzug: Er sollte immer im vollen Bewegungsradius ausgeführt werden.\n"
                + "\n"
                + "Das bedeutet für die Ausführung:\n"
                + "\n"
                + "- Im kompletten passiven Hang starten, die Muskulatur ist entspannt.\n"
                + "- Schulterblätter fixieren, indem sie nach hinten unten gezogen werden, und so in den aktiven Hang wechseln.\n"
                + "- Bauch anspannen und aus der Kraft der Arme und des oberen Rückens nach oben ziehen, bis die Stange vor dem Brustbein ist.\n"
                + "- Kurz halten und kontrolliert wieder ablassen.\n"
                + "- Aus dem passiven Hang erneut starten.",
            "https://www.uebungen.ws/wp-content/uploads/2011/07/klimmzuege.jpg",
            "https://youtu.be/T78xCiw_R6g");
    exerciseData.add(chinupsExerciseDataTemplate);

    final ExerciseData squatsExerciseDataTemplate =
        new ExerciseData(
            3,
            "Kniebeugen",
            "Kniebeugen Instruktionen hier einfügen ...",
            "https://www.uebungen.ws/wp-content/uploads/2011/08/Kniebeugen1.jpg",
            "https://youtu.be/huVujjfzphI");
    exerciseData.add(squatsExerciseDataTemplate);

    return exerciseData;
  }

  /**
   * Add some exercises to the training plan templates.
   *
   * @return training plan with entries.
   */
  public List<PlanEntry> createPlanEntryTemplates() {
    final List<PlanEntry> planEntryList = new ArrayList<>();

    final List<ExerciseData> exerciseDataList = createExerciseDataTemplates();

    planEntryList.add(
        new PlanEntry(1, "0kg", "0x0", exerciseDataList.get(1).getExerciseDataId(), 1));
    planEntryList.add(
        new PlanEntry(2, "5kg", "3x12", exerciseDataList.get(1).getExerciseDataId(), 1));
    planEntryList.add(
        new PlanEntry(3, "5kg", "3x15", exerciseDataList.get(2).getExerciseDataId(), 1));

    return planEntryList;
  }

  /**
   * create training plans with entries.
   *
   * @return list of training plans.
   */
  public List<TrainingPlan> createTrainingPlanTemplates() {
    final List<TrainingPlan> trainingPlanList = new ArrayList<>();

    TrainingPlan trainingPlanTemplate1 = new TrainingPlan(1, "Trainingsplan 1");
    trainingPlanList.add(trainingPlanTemplate1);

    TrainingPlan trainingPlanTemplate2 = new TrainingPlan(2, "Trainingsplan 2");
    trainingPlanList.add(trainingPlanTemplate2);

    return trainingPlanList;
  }

  public void addMuscleGroups() {
    String[] muscleGroupArray = {"Beine", "Brust", "Arme", "Schultern", "Bauch", "Rücken"};

    for (int i = 0; i < muscleGroupArray.length; i++) {
      DatabaseManager.appDatabase.muscleGroupDao().insert(new MuscleGroup(i, muscleGroupArray[i]));
    }
  }
}
