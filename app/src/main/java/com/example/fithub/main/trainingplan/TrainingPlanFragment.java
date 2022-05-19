package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.components.TemplateSpinner;
import com.example.fithub.main.prototypes.ExerciseData;
import com.example.fithub.main.prototypes.Templates;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TrainingPlanFragment extends Fragment {
  private Serializer serializer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_training_plan, container, false);

    initSpinner(view);
    initTable(view);

    final Button buttonExercise = (Button) view.findViewById(R.id.button_exercise);
    buttonExercise.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(R.id.action_training_plan_to_exercise);
          }
        });

    return view;
  }

  /**
   * Initializes the table dynamically with trainings plan details.
   *
   * @param view the table is attached to in layout file
   */
  public void initTable(View view) {
    final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

    // duplicate code for testing purpose will be erased later
    this.serializer = new Serializer();
    Type listOfExercisesType = new TypeToken<List<ExerciseData>>() {}.getType();

    List<ExerciseData> exerciseDataTemplates =
        (List<ExerciseData>)
            this.serializer.deserialize(
                getActivity(), listOfExercisesType, Savefile.EXERCISE_SAVEFILE);

    // Templates need to be created if file is corrupted or not existent
    if (exerciseDataTemplates == null) {
      Templates templates = new Templates();
      exerciseDataTemplates = templates.createExerciseTemplates();
    }

    for (int i = 0; i < exerciseDataTemplates.size(); i++) {
      addTableRow(tableLayout, exerciseDataTemplates.get(i));
    }
  }

  /**
   * Add entry to the table.
   *
   * @param tableLayout entry is added to
   * @param exerciseData exercise data for the table entry
   */
  void addTableRow(TableLayout tableLayout, ExerciseData exerciseData) {
    TableRow tableRow = new TableRow(getActivity());
    tableRow.setLayoutParams(
        new TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
    tableRow.setWeightSum(3.0f);

    final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
    layoutParams.width = 0;
    layoutParams.height = TableRow.LayoutParams.WRAP_CONTENT;
    // layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
    layoutParams.weight = 1.0f;

    final TextView textViewExercise = new TextView(getActivity());
    textViewExercise.setText("Übung 1");
    textViewExercise.setTextSize(14);
    textViewExercise.setPadding(10, 10, 10, 10);
    textViewExercise.setLayoutParams(layoutParams);

    final TextView textViewWeight = new TextView(getActivity());
    textViewWeight.setText("50 KG");
    textViewWeight.setTextSize(14);
    textViewWeight.setPadding(10, 10, 10, 10);
    textViewWeight.setLayoutParams(layoutParams);

    final TextView textViewRepeats = new TextView(getActivity());
    textViewRepeats.setText("3x 12");
    textViewRepeats.setTextSize(14);
    textViewRepeats.setPadding(10, 10, 10, 10);
    textViewRepeats.setLayoutParams(layoutParams);

    tableRow.addView(textViewExercise);
    tableRow.addView(textViewWeight);
    tableRow.addView(textViewRepeats);

    textViewRepeats.setTextSize(14);

    tableLayout.addView(tableRow);
  }

  /**
   * Initializes a spinner with a list of templates.
   *
   * @param view the spinner is attached to in layout file
   */
  public void initSpinner(View view) {
    final TemplateSpinner spinner =
        new TemplateSpinner(view, getActivity(), R.id.spinner_training_plan);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
