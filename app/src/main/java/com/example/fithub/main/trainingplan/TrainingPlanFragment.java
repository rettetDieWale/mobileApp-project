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
import com.example.fithub.main.prototypes.Exercise;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.ExerciseData;
import com.example.fithub.main.storage.Serializer;

import java.util.ArrayList;
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

    return view;
  }

  /**
   * Initializes the table dynamically with trainings plan details.
   *
   * @param view the table is attached to in layout file
   */
  public void initTable(View view) {
    final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

    List<ExerciseData> exerciseDataList = DatabaseManager.appDatabase.exerciseDataDao().getAll();

    final int FIRST = 0;
    final ExerciseData startupTemplateExerciseData = exerciseDataList.get(FIRST);
    // exerciseData.remove(FIRST);

    setNewExerciseButton(view, startupTemplateExerciseData);

    final List<Exercise> exerciseList = new ArrayList<>();

    for (int i = 0; i < exerciseDataList.size(); i++) {
      final String name = exerciseDataList.get(i).getName();
      exerciseList.add(new Exercise(name, "20kg", "12x3", exerciseDataList.get(i)));
    }

    for (int i = 0; i < exerciseList.size(); i++) {
      addTableRow(tableLayout, exerciseList.get(i));
    }
  }

  /**
   * Add entry to the table.
   *
   * @param tableLayout entry is added to
   * @param exercise exercise data for the table entry
   */
  void addTableRow(TableLayout tableLayout, Exercise exercise) {
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
    textViewExercise.setText(exercise.getName());
    textViewExercise.setTextSize(14);
    textViewExercise.setPadding(10, 10, 10, 10);
    textViewExercise.setLayoutParams(layoutParams);

    final TextView textViewWeight = new TextView(getActivity());
    textViewWeight.setText(exercise.getWeight());
    textViewWeight.setTextSize(14);
    textViewWeight.setPadding(10, 10, 10, 10);
    textViewWeight.setLayoutParams(layoutParams);

    final TextView textViewRepeats = new TextView(getActivity());
    textViewRepeats.setText(exercise.getRepeats());
    textViewRepeats.setTextSize(14);
    textViewRepeats.setPadding(10, 10, 10, 10);
    textViewRepeats.setLayoutParams(layoutParams);

    tableRow.addView(textViewExercise);
    tableRow.addView(textViewWeight);
    tableRow.addView(textViewRepeats);

    textViewRepeats.setTextSize(14);

    textViewExercise.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable("exercise", exercise.getExerciseData());

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(R.id.action_training_plan_to_exercise, args);
          }
        });

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

  public void setNewExerciseButton(View view, ExerciseData exerciseData) {
    final Button buttonExercise = (Button) view.findViewById(R.id.button_exercise);
    buttonExercise.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable("exercise", exerciseData);

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(R.id.action_training_plan_to_exercise, args);
          }
        });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
