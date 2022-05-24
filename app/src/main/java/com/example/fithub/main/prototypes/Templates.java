package com.example.fithub.main.prototypes;

import com.example.fithub.main.prototypes.data.ExerciseData;
import com.example.fithub.main.prototypes.data.PlanEntry;

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
            "Klimmzüge Instruktionen hier einfügen ...",
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

  public List<PlanEntry> createPlanEntryTemplates() {
    final List<PlanEntry> planEntryList = new ArrayList<>();

    final List<ExerciseData> exerciseDataList = createExerciseDataTemplates();

    planEntryList.add(new PlanEntry(1, "0kg", "0x0", exerciseDataList.get(0).getExerciseDataId()));
    planEntryList.add(new PlanEntry(2, "5kg", "3x12", exerciseDataList.get(1).getExerciseDataId()));
    planEntryList.add(new PlanEntry(3, "5kg", "3x15", exerciseDataList.get(2).getExerciseDataId()));

    return planEntryList;
  }
}
