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
  public List<Exercise> createExerciseTemplates() {
    final List<Exercise> exercises = new ArrayList<>();

    final Exercise emptyExerciseTemplate = new Exercise();
    emptyExerciseTemplate.setName("Hier Übungsnamen eingeben...");
    emptyExerciseTemplate.setInstruction("Hier Anleitung einfügen...");
    emptyExerciseTemplate.setVideoUrl("Hier Video Url einfügen...");
    emptyExerciseTemplate.setImageId(0);

    exercises.add(emptyExerciseTemplate);

    final Exercise chinupsExerciseTemplate = new Exercise();

    chinupsExerciseTemplate.setName("Klimmzug");
    chinupsExerciseTemplate.setInstruction("Klimmzüge Instruktionen hier einfügen ...");
    chinupsExerciseTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/T78xCiw_R6g\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    chinupsExerciseTemplate.setImageId(R.drawable.klimmzug);

    exercises.add(chinupsExerciseTemplate);

    final Exercise squatsExerciseTemplate = new Exercise();

    squatsExerciseTemplate.setName("Kniebeugen");
    squatsExerciseTemplate.setInstruction("Kniebeugen Instruktionen hier einfügen ...");
    squatsExerciseTemplate.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/huVujjfzphI\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    squatsExerciseTemplate.setImageId(R.drawable.klimmzug);

    exercises.add(squatsExerciseTemplate);

    return exercises;
  }
}
