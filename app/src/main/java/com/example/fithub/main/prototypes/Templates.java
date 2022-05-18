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
    List<Exercise> exercises = new ArrayList<Exercise>();
    Exercise exercise = new Exercise();

    exercise.setName("Klimmzug");
    exercise.setInstruction("Klimmzüge Instruktionen hier einfügen ...");
    exercise.setVideoUrl(
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/T78xCiw_R6g\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
    exercise.setImageId(R.drawable.klimmzug);

    exercises.add(exercise);

    return exercises;
  }
}
