package com.example.fithub.main.prototypes;

import com.example.fithub.R;

import java.util.ArrayList;
import java.util.List;

/** Helper class to create templates or reset exercises to factory settings. */
public class Templates {

  /**
   * Creates the exercise templates that are hardcoded.
   *
   * @return List with template exercises
   */
  public List<ExerciseData> createExerciseTemplates() {
    final List<ExerciseData> exerciseData = new ArrayList<>();

    final ExerciseData emptyExerciseDataTemplate = new ExerciseData();
    emptyExerciseDataTemplate.setName("Hier Übungsnamen eingeben...");
    emptyExerciseDataTemplate.setInstruction("Hier Anleitung einfügen...");
    emptyExerciseDataTemplate.setVideoUrl("Hier Video Url einfügen...");
    emptyExerciseDataTemplate.setImageId(0);

    exerciseData.add(emptyExerciseDataTemplate);

    final ExerciseData chinupsExerciseDataTemplate = new ExerciseData();

    chinupsExerciseDataTemplate.setName("Klimmzug");
    chinupsExerciseDataTemplate.setInstruction("Klimmzüge Instruktionen hier einfügen ...");
    chinupsExerciseDataTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/T78xCiw_R6g\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    chinupsExerciseDataTemplate.setImageId(R.drawable.klimmzug);

    exerciseData.add(chinupsExerciseDataTemplate);

    final ExerciseData squatsExerciseDataTemplate = new ExerciseData();

    squatsExerciseDataTemplate.setName("Kniebeugen");
    squatsExerciseDataTemplate.setInstruction("Kniebeugen Instruktionen hier einfügen ...");
    squatsExerciseDataTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/huVujjfzphI\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    squatsExerciseDataTemplate.setImageId(R.drawable.klimmzug);

    exerciseData.add(squatsExerciseDataTemplate);

    return exerciseData;
  }
}
