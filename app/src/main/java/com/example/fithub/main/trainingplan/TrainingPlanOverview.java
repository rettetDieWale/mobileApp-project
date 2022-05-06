package com.example.fithub.main.trainingplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingPlanOverviewBinding;
import com.example.fithub.main.calendar.CalendarActivity;

/**
 * A simple {@link Fragment} subclass. Use the {@link TrainingPlanOverview#newInstance} factory
 * method to create an instance of this fragment.
 */
public class TrainingPlanOverview extends Fragment {
  private FragmentTrainingPlanOverviewBinding binding;

  public TrainingPlanOverview() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @return A new instance of fragment TrainingPlanOverview.
   */
  public static TrainingPlanOverview newInstance(String param1, String param2) {
    TrainingPlanOverview fragment = new TrainingPlanOverview();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentTrainingPlanOverviewBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.buttonTrainingplanReturn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    binding.buttonTrainingPlan.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            NavHostFragment.findNavController(TrainingPlanOverview.this)
                .navigate(R.id.action_training_plan_overview_to_training_plan);
          }
        });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
