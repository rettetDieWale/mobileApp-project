package com.example.fithub.main.components;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.ExerciseData;

import java.util.ArrayList;
import java.util.List;

/** Interface class for spinners selecting our templates. */
public class TemplateSpinner {
  private Spinner spinner;

  /**
   * Instantiates a new template spinner.
   *
   * @param view the spinner is attached to
   * @param context of the activity the spinner is used
   * @param spinnerId R.id of the component the adapter is set
   */
  public TemplateSpinner(View view, Context context, int spinnerId) {
    this.spinner = (Spinner) view.findViewById(spinnerId);

    ArrayList<String> exerciseNames = getExerciseNames();

    ArrayAdapter<String> adapter =
        new ArrayAdapter<String>(
            context, android.R.layout.simple_spinner_dropdown_item, exerciseNames);

    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Apply the adapter to the spinner
    this.spinner.setAdapter(adapter);
  }

  /**
   * get exercise data list from storage and gathers name in list.
   *
   * @return list of names as strings.
   */
  public ArrayList<String> getExerciseNames() {
    final List<ExerciseData> exerciseDataList =
        DatabaseManager.appDatabase.exerciseDataDao().getAll();

    final ArrayList<String> exerciseDataNames = new ArrayList<>();
    for (int i = 0; i < exerciseDataList.size(); i++) {
      exerciseDataNames.add(exerciseDataList.get(i).getName());
    }

    return exerciseDataNames;
  }
}
