package com.example.fithub.main.trainingplan;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.components.Item;
import com.example.fithub.main.components.TemplateSpinner;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingPlan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlanFragment extends Fragment {
  private final int STANDARD_TEMPLATE_ID = 1;
  private View view;
  private TrainingPlan currentTrainingPlan;
  private TableLayout tableLayout;
  private int actionId;
  private TemplateSpinner templateSpinner;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_training_plan, container, false);

    this.tableLayout = (TableLayout) view.findViewById(R.id.table_layout_include);

    final Bundle bundle = getArguments();
    final int trainingPlanId = bundle.getInt("trainingPlanId");
    this.actionId = bundle.getInt("actionId");

    setCurrentPlanObject(trainingPlanId);
    updateTrainingPlanTable();

    final ImageButton deletePlanButton = view.findViewById(R.id.deletePlanButton);
    deletePlanButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            final Item item = (Item) templateSpinner.getSpinner().getSelectedItem();
            setCurrentPlanObject(item.getId());

            if (!(currentTrainingPlan.getTrainingPlanId() == STANDARD_TEMPLATE_ID)) {
              try {

                List<PlanEntry> planEntryList =
                    DatabaseManager.appDatabase
                        .planEntryDao()
                        .getPlanEntryListByPlanId(currentTrainingPlan.getTrainingPlanId());
                for (int i = 0; i < planEntryList.size(); i++) {
                  DatabaseManager.appDatabase.planEntryDao().delete(planEntryList.get(i));
                }

                DatabaseManager.appDatabase
                    .trainingPlanDao()
                    .delete(
                        DatabaseManager.appDatabase
                            .trainingPlanDao()
                            .getById(currentTrainingPlan.getTrainingPlanId()));

                getActivity().onBackPressed();

              } catch (SQLiteConstraintException sqLiteConstraintException) {
                Toast.makeText(
                        getActivity(),
                        " Trainingsplan wird noch ein einem Trainingstag verwendet!",
                        Toast.LENGTH_SHORT)
                    .show();
              }
            } else {
              Toast.makeText(
                      getActivity(),
                      "Standard Templates können nicht gelöscht werden!",
                      Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });

    initPlanSpinner();

    return view;
  }

  /**
   * Updates the current training plan object and sets it to the plan with the given id from
   * storage.
   *
   * @param planId of the plan object
   */
  private void setCurrentPlanObject(final int planId) {
    this.currentTrainingPlan = DatabaseManager.appDatabase.trainingPlanDao().getById(planId);
  }

  /**
   * Create training plan items for the spinner.
   *
   * @return list of items
   */
  private ArrayList<Item> createItemList() {
    final List<TrainingPlan> trainingPlanList =
        DatabaseManager.appDatabase.trainingPlanDao().getAll();

    final ArrayList<Item> items = new ArrayList<>();

    for (int i = 0; i < trainingPlanList.size(); i++) {
      int trainingPlanId = trainingPlanList.get(i).getTrainingPlanId();
      final String trainingPlanName = trainingPlanList.get(i).getName();
      items.add(new Item(trainingPlanId, trainingPlanName));
    }

    return items;
  }

  /**
   * Adds an item selection listener to the spinner that fetches the chosen training plan data from
   * storage and sets the fragment content accordingly.
   *
   * @param templateSpinner that item selection listener is attached to
   */
  private void setItemSelectionListener(final TemplateSpinner templateSpinner) {
    final Spinner spinner = templateSpinner.getSpinner();
    spinner.setOnItemSelectedListener(
        new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            final Item spinnerItem = (Item) adapterView.getItemAtPosition(position);
            final int id = spinnerItem.getId();
            setCurrentPlanObject(id);
            resetTableRows();
            updateTrainingPlanTable();
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {}
        });
  }

  /** Initializes the training plan spinner. */
  private void initPlanSpinner() {

    final ArrayList<Item> items = createItemList();

    this.templateSpinner =
        new TemplateSpinner(view, getActivity(), R.id.spinner_training_plan, items);

    setItemSelectionListener(templateSpinner);

    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getId() == this.currentTrainingPlan.getTrainingPlanId()) {
        templateSpinner.setItemSelected(items.get(i));
      }
    }
  }

  /** Updates the training plan table and puts all entries from storage inside it. */
  private void updateTrainingPlanTable() {

    final String trainingPlanName = this.currentTrainingPlan.getName();
    final String notice = this.currentTrainingPlan.getNotice();

    final List<PlanEntry> planEntryList =
        DatabaseManager.appDatabase
            .planEntryDao()
            .getPlanEntryListByPlanId(this.currentTrainingPlan.getTrainingPlanId());

    final TextInputEditText inputPlanName = view.findViewById(R.id.inputPlanName);
    inputPlanName.setText(trainingPlanName);

    final TextInputEditText noticeEditText = view.findViewById(R.id.notice_textEdit);
    noticeEditText.setText(notice);

    final Button changePlanNameButton = view.findViewById(R.id.buttonChangeName);

    changePlanNameButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            currentTrainingPlan.setName(inputPlanName.getEditableText().toString());
            currentTrainingPlan.setNotice(noticeEditText.getEditableText().toString());

            DatabaseManager.appDatabase.trainingPlanDao().update(currentTrainingPlan);
          }
        });

    initTable(planEntryList);
  }

  /** Initializes the table dynamically with trainings plan details. */
  private void initTable(final List<PlanEntry> planEntryList) {

    attachAddButton();

    for (int i = 0; i < planEntryList.size(); i++) {
      addTableRow(planEntryList.get(i));
    }
  }

  /** Attaches the button to add exercises into the table. */
  private void attachAddButton() {
    final ImageButton addButton =
        new ImageButton(new ContextThemeWrapper(getActivity(), R.style.small_buttons), null, 0);
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
                new PlanEntry(
                    0,
                    "0kg",
                    "3x12 ",
                    STANDARD_TEMPLATE_ID,
                    currentTrainingPlan.getTrainingPlanId());
            DatabaseManager.appDatabase.planEntryDao().insert(planEntry);

            // need to fetch last entry from the list because ids are auto generated
            List<PlanEntry> planEntryList = DatabaseManager.appDatabase.planEntryDao().getAll();
            planEntry = planEntryList.get(planEntryList.size() - 1);

            addTableRow(planEntry);
          }
        });
  }
  /**
   * Add entry to the table.
   *
   * @param trainingPlanEntry exercise data for the table entry
   */
  private void addTableRow(final PlanEntry trainingPlanEntry) {
    final TableRow tableRow = new TableRow(getActivity());
    tableRow.setLayoutParams(
        new TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
    tableRow.setWeightSum(3.0f);

    final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
    layoutParams.width = 0;
    layoutParams.height = TableRow.LayoutParams.WRAP_CONTENT;
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
            final PlanEntry planeEntry =
                DatabaseManager.appDatabase
                    .planEntryDao()
                    .getPlanEntryById(trainingPlanEntry.getEntryId());
            planeEntry.setWeight(editable.toString());
            DatabaseManager.appDatabase.planEntryDao().update(planeEntry);
          }
        });

    final TextView textViewRepeats = new TextInputEditText(getActivity());
    textViewRepeats.setText(trainingPlanEntry.getRepeats());
    textViewRepeats.setTextSize(14);
    textViewRepeats.setPadding(10, 10, 10, 10);
    textViewRepeats.setLayoutParams(layoutParams);
    textViewRepeats.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void afterTextChanged(Editable editable) {
            final PlanEntry planeEntry =
                DatabaseManager.appDatabase
                    .planEntryDao()
                    .getPlanEntryById(trainingPlanEntry.getEntryId());

            planeEntry.setRepeats(editable.toString());
            DatabaseManager.appDatabase.planEntryDao().update(planeEntry);
          }
        });

    final ImageButton deleteRowButton =
        new ImageButton(new ContextThemeWrapper(getActivity(), R.style.small_buttons), null, 0);
    deleteRowButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_delete));

    deleteRowButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DatabaseManager.appDatabase.planEntryDao().delete(trainingPlanEntry);
            resetTableRows();
            updateTrainingPlanTable();
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

            // navigation to  exercise is possible from either training day or training plan
            int targetActionId = 0;
            if (actionId == 0)
              targetActionId = R.id.action_trainingPlanFragment_to_exerciseFragment;
            else targetActionId = R.id.action_trainingDayFragment_to_exerciseFragment;

            NavHostFragment.findNavController(TrainingPlanFragment.this)
                .navigate(targetActionId, args);
          }
        });

    tableLayout.addView(tableRow);
  }

  /** Resets the table for a table layout. */
  private void resetTableRows() {
    // position 0+1 are used for header and add button (don't remove those)
    final int FIRST_DATA_POSITION = 1;
    tableLayout.removeViews(FIRST_DATA_POSITION, tableLayout.getChildCount() - 1);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  public TrainingPlan getCurrentTrainingPlan() {
    return this.currentTrainingPlan;
  }
}
