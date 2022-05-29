package com.example.fithub.main.trainingplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;
import com.example.fithub.main.calendar.CalendarActivity;
import com.example.fithub.main.components.ListAdapter;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingPlan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlanOverviewFragment extends Fragment {

  private List<String> names;
  private View view;
  private TextInputEditText planNameInput;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.view = inflater.inflate(R.layout.fragment_training_plan_overview, container, false);

    this.planNameInput = view.findViewById(R.id.planNameInput);
    planNameInput.setText("Trainingsplan Name", TextView.BufferType.EDITABLE);

    final Button returnButton = view.findViewById(R.id.button_trainingplan_return);
    returnButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    updateListView();

    final ImageButton buttonAddPlan = view.findViewById(R.id.buttonAddPlan);
    buttonAddPlan.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            DatabaseManager.appDatabase
                .trainingPlanDao()
                .insert(new TrainingPlan(0, planNameInput.getEditableText().toString()));
            updateListView();
          }
        });

    return view;
  }

  /** Updates the recycler view /list view with all training plans from storage. */
  public void updateListView() {
    this.names = new ArrayList<>();

    List<TrainingPlan> trainingPlanList = DatabaseManager.appDatabase.trainingPlanDao().getAll();
    int[] trainingPlanIds = new int[trainingPlanList.size()];

    for (int i = 0; i < trainingPlanList.size(); i++) {
      names.add(trainingPlanList.get(i).getName());
      trainingPlanIds[i] = trainingPlanList.get(i).getTrainingPlanId();
    }

    final RecyclerView recyclerView = view.findViewById(R.id.listLayout);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(
        new ListAdapter(names, trainingPlanIds, TrainingPlanOverviewFragment.this));
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
