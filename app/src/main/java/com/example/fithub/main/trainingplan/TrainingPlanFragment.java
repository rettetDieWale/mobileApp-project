package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;

public class TrainingPlanFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_training_plan, container, false);

    Spinner spinner = (Spinner) view.findViewById(R.id.spinner_training_plan);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            getActivity(), R.array.template_array, android.R.layout.simple_spinner_item);

    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);

    final Button buttonExercise = (Button) view.findViewById(R.id.button_exercise);
    buttonExercise.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(R.id.action_training_plan_to_exercise);
          }
        });

    // table
    final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

    TableRow tableRow = new TableRow(getActivity());
    tableRow.setLayoutParams(
        new TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
    tableRow.setWeightSum(3.0f);

    final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
    layoutParams.width = 0;
    layoutParams.height = TableRow.LayoutParams.WRAP_CONTENT;
    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
    layoutParams.weight = 1.0f;

    final TextView textViewExercise = new TextView(getActivity());
    textViewExercise.setText("Übung 1");
    textViewExercise.setTextSize(14);
    textViewExercise.setLayoutParams(layoutParams);

    final TextView textViewWeight = new TextView(getActivity());
    textViewWeight.setText("50 KG");
    textViewWeight.setTextSize(14);
    textViewWeight.setLayoutParams(layoutParams);

    final TextView textViewRepeats = new TextView(getActivity());
    textViewRepeats.setText("3x 12");
    textViewRepeats.setTextSize(14);
    textViewRepeats.setLayoutParams(layoutParams);

    //
    tableRow.addView(textViewExercise);
    tableRow.addView(textViewWeight);
    tableRow.addView(textViewRepeats);

    textViewRepeats.setTextSize(14);

    tableLayout.addView(tableRow);

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
