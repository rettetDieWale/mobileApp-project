package com.example.fithub.main.calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingDayBinding;
import com.example.fithub.main.components.Item;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;
import com.example.fithub.main.prototypes.data.relations.TrainingDayWithMuscleGroups;

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
  private boolean isArchived = false;
  private ImageButton deleteTrainingDayButton;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_training_day, container, false);

    deleteTrainingDayButton = this.view.findViewById(R.id.delete_training_day);

    loadTrainingDayData();
    addSaveButtonListener();

    muscleGroupView = view.findViewById(R.id.muscle_group_view);
    selectedMuscleGroups = new boolean[muscleGroupArray.length];
    muscleGroupList = new ArrayList<>();

    final Date trainingDayDate = DateConverter.parseStringToDate(dateTextView.getText().toString());

    final List<TrainingDayWithMuscleGroups> trainingDayWithMuscleGroups =
        DatabaseManager.appDatabase
            .trainingDayDao()
            .getTrainingDaysWithMuscleGroupsByDate(trainingDayDate);

    if (trainingDayWithMuscleGroups.size() != 0) {
      List<MuscleGroup> storedMuscleGroupData = trainingDayWithMuscleGroups.get(0).muscleGroupList;

      final StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < storedMuscleGroupData.size(); i++) {
        final int muscleGroupId = storedMuscleGroupData.get(i).muscleGroupId;
        final String muscleGroupName = storedMuscleGroupData.get(i).getMuscleGroupName();

        selectedMuscleGroups[muscleGroupId] = true;
        muscleGroupList.add(muscleGroupId);

        stringBuilder.append(muscleGroupName);
        if (i != storedMuscleGroupData.size() - 1) stringBuilder.append(", ");
      }
      muscleGroupView.setText(stringBuilder.toString());
    }

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

    final ImageButton archiveButton = view.findViewById(R.id.archive_button);
    if (isArchived) {
      grayOutImageButton(archiveButton);
    } else {
      archiveButton.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              grayOutImageButton(archiveButton);
              isArchived = true;
              saveTrainingDayData();
              Toast.makeText(getActivity(), "Trainingstag Archiviert!", Toast.LENGTH_SHORT).show();
            }
          });
    }

    return view;
  }

  private void grayOutImageButton(ImageButton imageButton) {
    imageButton.setAlpha(.5f);
    imageButton.setClickable(false);
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
            saveTrainingDayData();
          }
        });
  }

  /** Save training day data into storage. */
  private void saveTrainingDayData() {
    final String dateString = dateTextView.getText().toString();
    final int wellBeing = Integer.parseInt(wellBeingView.getText().toString());

    final Spinner spinner = trainingPlanFragment.getView().findViewById(R.id.spinner_training_plan);
    final Item item = (Item) spinner.getSelectedItem();
    final int id = item.getId();

    Date trainingDayDate = DateConverter.parseStringToDate(dateString);

    DatabaseManager.appDatabase
        .trainingDayDao()
        .insert(new TrainingDay(trainingDayDate, id, wellBeing, isArchived));

    for (int i = 0; i < muscleGroupList.size(); i++) {
      DatabaseManager.appDatabase
          .trainingDayMuscleGroupCrossRefDao()
          .insert(new TrainingDayMuscleGroupCrossRef(trainingDayDate, muscleGroupList.get(i)));
    }

    revertGrayOutForDeleteButton(trainingDayDate);
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

    final ImageButton deleteButton = this.view.findViewById(R.id.delete_training_day);

    // possibility of training day not existing
    TrainingDay trainingDay = DatabaseManager.appDatabase.trainingDayDao().getByDate(date);
    if (trainingDay == null) {
      trainingDay = new TrainingDay(date, 1, 1, isArchived);
      grayOutImageButton(deleteButton);
    } else {
      final TrainingDay finalTrainingDay = trainingDay;
      revertGrayOutForDeleteButton(date);
    }

    this.wellBeingView.setText(String.valueOf(trainingDay.getWellBeing()));
    this.isArchived = trainingDay.isArchived();

    setupTrainingPlanFragment(trainingDay);
  }

  private void revertGrayOutForDeleteButton(Date trainingDayDate) {

    deleteTrainingDayButton.setAlpha(1f);
    deleteTrainingDayButton.setClickable(true);
    deleteTrainingDayButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DatabaseManager.appDatabase.trainingDayDao().deleteById(trainingDayDate);
            getActivity().onBackPressed();
          }
        });
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
