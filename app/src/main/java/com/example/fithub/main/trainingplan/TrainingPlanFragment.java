package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
