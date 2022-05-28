package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.List;

public class TrainingPlanFragment extends Fragment {
  private final int STANDARD_TEMPLATE_ID = 1;
  private View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_training_plan, container, false);

    final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout_include);

    final Bundle bundle = getArguments();
    final int trainingPlanId = bundle.getInt("trainingPlanId");

    final ImageButton addButton = view.findViewById(R.id.addButton);
    addButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            PlanEntry planEntry =
                new PlanEntry(0, "0kg", "3x12 ", STANDARD_TEMPLATE_ID, trainingPlanId);
            DatabaseManager.appDatabase.planEntryDao().insert(planEntry);

            // need to fetch last entry from the list because ids are auto generated
            List<PlanEntry> planEntryList = DatabaseManager.appDatabase.planEntryDao().getAll();
            planEntry = planEntryList.get(planEntryList.size() - 1);

            addTableRow(tableLayout, planEntry);
          }
        });

    getTrainingPlanData(trainingPlanId);

    return view;
  }

  /**
   * Gets data for the current training plan from storage and adds content to fragment components.
   */
  public void getTrainingPlanData(int trainingPlanId) {
    TrainingPlan currentTrainingPlan =
        DatabaseManager.appDatabase.trainingPlanDao().getById(trainingPlanId);

    String trainingPlanName = currentTrainingPlan.getName();

    List<PlanEntry> planEntryList =
        DatabaseManager.appDatabase.planEntryDao().getPlanEntrieListById(trainingPlanId);

    final TextView nameTextView = (TextView) view.findViewById(R.id.training_plan_name);

    nameTextView.setText(trainingPlanName);

    initTable(planEntryList);
  }

  /** Initializes the table dynamically with trainings plan details. */
  public void initTable(List<PlanEntry> planEntryList) {
    final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout_include);

    final int FIRST = 0;
    PlanEntry startupTemplateExercise;

    for (int i = 0; i < planEntryList.size(); i++) {
      addTableRow(tableLayout, planEntryList.get(i));
    }
  }

  /**
   * Add entry to the table.
   *
   * @param tableLayout entry is added to
   * @param trainingPlanEntry exercise data for the table entry
   */
  void addTableRow(TableLayout tableLayout, PlanEntry trainingPlanEntry) {
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
    textViewExercise.setText(
        DatabaseManager.appDatabase
            .exerciseDataDao()
            .getExerciseData(trainingPlanEntry.getExerciseDataId())
            .getName());
    textViewExercise.setTextSize(14);
    textViewExercise.setPadding(10, 10, 10, 10);
    textViewExercise.setLayoutParams(layoutParams);

    final TextView textViewWeight = new TextView(getActivity());
    textViewWeight.setText(trainingPlanEntry.getWeight());
    textViewWeight.setTextSize(14);
    textViewWeight.setPadding(10, 10, 10, 10);
    textViewWeight.setLayoutParams(layoutParams);

    final TextView textViewRepeats = new TextView(getActivity());
    textViewRepeats.setText(trainingPlanEntry.getRepeats());
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
            args.putInt("exerciseDataId", trainingPlanEntry.getExerciseDataId());
            args.putInt("entryId", trainingPlanEntry.getEntryId());

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(R.id.action_training_plan_to_exercise, args);
          }
        });

    tableLayout.addView(tableRow);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /**
   * Add the on click listener for the button to create new exercises.
   *
   * @param exerciseDataId of standard template
   */
  public void setNewExerciseButton(int exerciseDataId) {
    final ImageButton buttonExercise = view.findViewById(R.id.addButton);
    buttonExercise.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Bundle args = new Bundle();
            args.putInt("exerciseDataId", exerciseDataId);
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
