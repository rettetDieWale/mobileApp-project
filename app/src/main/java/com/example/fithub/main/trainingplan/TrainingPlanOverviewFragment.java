package com.example.fithub.main.trainingplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;
import com.example.fithub.main.calendar.CalendarActivity;
import com.example.fithub.main.components.ListAdapter;

public class TrainingPlanOverviewFragment extends Fragment {

  private final String[] names = {
    "hello", "bye", "main", "joe", "give", "true", "false", "know", "list", "hello", "bye"
  };

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_training_plan_overview, container, false);

    final Button returnButton = view.findViewById(R.id.button_trainingplan_return);
    returnButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    // ONLY FOR TEST PURPOSE WILL BE DELETED LATER
    final Button tpButton = view.findViewById(R.id.button_tp_for_test);
    tpButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            NavHostFragment.findNavController(TrainingPlanOverviewFragment.this)
                .navigate(R.id.action_training_plan_overview_to_training_plan);
          }
        });

    final RecyclerView recyclerView = view.findViewById(R.id.listLayout);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new ListAdapter(names));

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
