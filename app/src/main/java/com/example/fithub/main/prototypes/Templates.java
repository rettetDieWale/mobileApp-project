package com.example.fithub.main.prototypes;

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
    emptyExerciseDataTemplate.setImageUrl("Hier Bild URL einfügen...");

    exerciseData.add(emptyExerciseDataTemplate);

    final ExerciseData chinupsExerciseDataTemplate = new ExerciseData();

    chinupsExerciseDataTemplate.setName("Klimmzug");
    chinupsExerciseDataTemplate.setInstruction("Klimmzüge Instruktionen hier einfügen ...");
    chinupsExerciseDataTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/T78xCiw_R6g\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    chinupsExerciseDataTemplate.setImageUrl(
        "https://www.uebungen.ws/wp-content/uploads/2011/07/klimmzuege.jpg");

    exerciseData.add(chinupsExerciseDataTemplate);

    final ExerciseData squatsExerciseDataTemplate = new ExerciseData();

    squatsExerciseDataTemplate.setName("Kniebeugen");
    squatsExerciseDataTemplate.setInstruction("Kniebeugen Instruktionen hier einfügen ...");
    squatsExerciseDataTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/huVujjfzphI\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    squatsExerciseDataTemplate.setImageUrl(
        "https://www.uebungen.ws/wp-content/uploads/2011/08/Kniebeugen1.jpg");

    exerciseData.add(squatsExerciseDataTemplate);

    return exerciseData;
  }
}
