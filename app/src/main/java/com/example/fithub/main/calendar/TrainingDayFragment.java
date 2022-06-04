package com.example.fithub.main.calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingDayBinding;
import com.example.fithub.main.components.Item;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TrainingDayFragment extends Fragment {

  boolean[] selectedMuscleGroups;
  ArrayList<Integer> muscleGroupList;
  String[] muscleGroupArray = {"Beine", "Brust", "Arme", "Schultern", "Bauch", "Rücken"};

  private FragmentTrainingDayBinding binding;
  private View view;
  private TextView dateTextView;
  private EditText wellBeingView;
  private Fragment trainingPlanFragment;
  private TextView muscleGroupView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_training_day, container, false);

    loadTrainingDayData();
    addSaveButtonListener();

    muscleGroupView = view.findViewById(R.id.muscle_group_view);
    selectedMuscleGroups = new boolean[muscleGroupArray.length];
    muscleGroupList = new ArrayList<>();

    muscleGroupView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Muskelgruppe(n) auswählen");
            builder.setCancelable(false);

            builder.setMultiChoiceItems(
                muscleGroupArray,
                selectedMuscleGroups,
                new DialogInterface.OnMultiChoiceClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    if (b) {
                      muscleGroupList.add(i);
                      Collections.sort(muscleGroupList);
                    } else {
                      muscleGroupList.remove(Integer.valueOf(i));
                    }
                  }
                });

            builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < muscleGroupList.size(); j++) {
                      stringBuilder.append(muscleGroupArray[muscleGroupList.get(j)]);
                      if (j != muscleGroupList.size() - 1) stringBuilder.append(", ");
                    }
                    muscleGroupView.setText(stringBuilder.toString());
                  }
                });

            builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                  }
                });

            builder.setNeutralButton(
                "Clear all",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    for (int j = 0; j < selectedMuscleGroups.length; j++) {
                      selectedMuscleGroups[j] = false;
                      muscleGroupList.clear();
                      muscleGroupView.setText("");
                    }
                  }
                });
            builder.show();
          }
        });

    return view;
  }

  /**
   * Adds functionality to the save button so it saves training day data into storage upon being
   * pressed.
   */
  private void addSaveButtonListener() {
    final Button saveButton = this.view.findViewById(R.id.save_training_day);
    saveButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            final String dateString = dateTextView.getText().toString();
            final int wellBeing = Integer.parseInt(wellBeingView.getText().toString());

            final Spinner spinner =
                trainingPlanFragment.getView().findViewById(R.id.spinner_training_plan);
            final Item item = (Item) spinner.getSelectedItem();
            final int id = item.getId();

            DatabaseManager.appDatabase
                .trainingDayDao()
                .insert(
                    new TrainingDay(DateConverter.parseStringToDate(dateString), id, wellBeing));
          }
        });
  }

  /**
   * initializes the training plan fragment that is included with the proper details.
   *
   * @param trainingDay which plan data should be used
   */
  private void setupTrainingPlanFragment(TrainingDay trainingDay) {
    final FragmentManager fragmentManager = getChildFragmentManager();
    final List<Fragment> fragmentList = fragmentManager.getFragments();

    final Bundle b = new Bundle();
    b.putInt("trainingPlanId", trainingDay.getTrainingPlanId());
    b.putInt("actionId", 1);

    fragmentList.get(0).setArguments(b);
    this.trainingPlanFragment = fragmentList.get(0);
  }

  /** Setup components with training day data from storage. */
  private void loadTrainingDayData() {
    this.wellBeingView = this.view.findViewById(R.id.well_being_value);

    setDate();

    final TextView dateTextView = this.view.findViewById(R.id.dateText);
    final Date date = DateConverter.parseStringToDate(dateTextView.getText().toString());

    // possibility of training day not existing
    TrainingDay trainingDay = DatabaseManager.appDatabase.trainingDayDao().getByDate(date);
    if (trainingDay == null) {
      trainingDay = new TrainingDay(date, 1, 1);
    }

    this.wellBeingView.setText(String.valueOf(trainingDay.getWellBeing()));

    setupTrainingPlanFragment(trainingDay);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  /** Set the text view date value. */
  private void setDate() {
    Bundle bundle = getArguments();
    Date date = null;
    if (bundle != null) {
      date = (Date) bundle.getSerializable("date");
    }
    this.dateTextView = this.view.findViewById(R.id.dateText);
    final String dateString = DateConverter.parseDateToString(date);
    this.dateTextView.setText(dateString);
  }
}
