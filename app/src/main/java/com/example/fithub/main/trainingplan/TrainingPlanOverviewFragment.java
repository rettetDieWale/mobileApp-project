package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;
import com.example.fithub.main.components.Item;
import com.example.fithub.main.components.ListAdapter;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlanOverviewFragment extends Fragment {
  private View view;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.view = inflater.inflate(R.layout.fragment_training_plan_overview, container, false);

    updateListView();

    final ImageButton buttonAddPlan = view.findViewById(R.id.buttonAddPlan);
    buttonAddPlan.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            DatabaseManager.appDatabase
                .trainingPlanDao()
                .insert(new TrainingPlan(0, "Trainingsplan Name"));
            updateListView();
          }
        });

    return view;
  }

  /** Updates the recycler view /list view with all training plans from storage. */
  public void updateListView() {

    final List<TrainingPlan> trainingPlanList =
        DatabaseManager.appDatabase.trainingPlanDao().getAll();
    final List<Item> trainingPlanItems = new ArrayList<>();

    for (int i = 0; i < trainingPlanList.size(); i++) {
      trainingPlanItems.add(
          new Item(trainingPlanList.get(i).getTrainingPlanId(), trainingPlanList.get(i).getName()));
    }

    final RecyclerView recyclerView = view.findViewById(R.id.listLayout);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new ListAdapter(trainingPlanItems, TrainingPlanOverviewFragment.this));
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
