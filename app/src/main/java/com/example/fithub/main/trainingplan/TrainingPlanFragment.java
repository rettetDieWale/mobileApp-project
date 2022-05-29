package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TrainingPlanFragment extends Fragment {
  private final int STANDARD_TEMPLATE_ID = 1;
  private View view;
  private int trainingPlanId;

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
    this.trainingPlanId = bundle.getInt("trainingPlanId");

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

    attachAddButton(tableLayout);

    for (int i = 0; i < planEntryList.size(); i++) {
      addTableRow(tableLayout, planEntryList.get(i));
    }
  }

  /**
   * Attaches the button to add exercises into the table.
   *
   * @param tableLayout the button is attached to
   */
  public void attachAddButton(TableLayout tableLayout) {
    final ImageButton addButton = new ImageButton(getActivity());
    addButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add));

    final TableRow tableRow = new TableRow(getActivity());
    final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
    layoutParams.width = TableRow.LayoutParams.MATCH_PARENT;
    layoutParams.height = TableRow.LayoutParams.MATCH_PARENT;
    layoutParams.span = 3;

    tableRow.addView(addButton);
    tableLayout.addView(tableRow);

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

    final TextView textViewWeight = new TextInputEditText(getActivity());
    textViewWeight.setText(trainingPlanEntry.getWeight());
    textViewWeight.setTextSize(14);
    textViewWeight.setPadding(10, 10, 10, 10);
    textViewWeight.setLayoutParams(layoutParams);
    textViewWeight.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void afterTextChanged(Editable editable) {
            PlanEntry planeEntry =
                DatabaseManager.appDatabase
                    .planEntryDao()
                    .getPlanEntryById(trainingPlanEntry.getEntryId());
            planeEntry.setWeight(editable.toString());
            DatabaseManager.appDatabase.planEntryDao().update(planeEntry);
          }
        });

    final TextView textViewRepeats = new TextView(getActivity());
    textViewRepeats.setText(trainingPlanEntry.getRepeats());
    textViewRepeats.setTextSize(14);
    textViewRepeats.setPadding(10, 10, 10, 10);
    textViewRepeats.setLayoutParams(layoutParams);

    final ImageButton deleteRowButton = new ImageButton(getActivity());
    deleteRowButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_delete));

    deleteRowButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DatabaseManager.appDatabase.planEntryDao().delete(trainingPlanEntry);
            // position 0+1 are used for header and add button (don't remove those)
            final int FIRST_DATA_POSITION = 1;
            tableLayout.removeViews(FIRST_DATA_POSITION, tableLayout.getChildCount() - 1);
            getTrainingPlanData(trainingPlanId);
          }
        });

    tableRow.addView(textViewExercise);
    tableRow.addView(textViewWeight);
    tableRow.addView(textViewRepeats);
    tableRow.addView(deleteRowButton);

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

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
