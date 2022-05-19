package com.example.fithub.main.prototypes;

import com.example.fithub.R;

import java.util.ArrayList;
import java.util.List;

/** Helper class to create templates or reset exercises to factory settings. */
public class Templates {

  /**
   * Creates the exercise templates.
   *
   * @return List with template exercises
   */
  public List<Exercise> createExerciseTemplates() {
    final List<Exercise> exercises = new ArrayList<Exercise>();
    final Exercise exercise = new Exercise();

    Exercise emptyExercise = new Exercise();
    emptyExercise.setName("Hier Übungsnamen eingeben...");
    emptyExercise.setInstruction("Hier Anleitung einfügen...");
    emptyExercise.setVideoUrl("Hier Video Url einfügen...");
    emptyExercise.setImageId(0);

    exercises.add(emptyExercise);

    exercise.setName("Klimmzug");
    exercise.setInstruction("Klimmzüge Instruktionen hier einfügen ...");
    exercise.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/T78xCiw_R6g\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    exercise.setImageId(R.drawable.klimmzug);

    exercises.add(exercise);

    final Exercise exercise2 = new Exercise();
    exercise2.setName("Kniebeugen");
    exercise2.setInstruction("Kniebeugen Instruktionen hier einfügen ...");
    exercise2.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/huVujjfzphI\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    exercise2.setImageId(R.drawable.klimmzug);

    exercises.add(exercise2);

    return exercises;
  }
}
