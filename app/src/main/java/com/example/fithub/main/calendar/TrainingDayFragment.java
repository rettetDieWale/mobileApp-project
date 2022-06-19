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
import com.example.fithub.main.components.Item;
import com.example.fithub.main.prototypes.ExperienceBar;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;
import com.example.fithub.main.prototypes.data.relations.TrainingDayWithMuscleGroups;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/** The type Training day fragment. */
public class TrainingDayFragment extends Fragment {

  private final String[] muscleGroupArray = {
    "Beine", "Brust", "Arme", "Schultern", "Bauch", "Rücken"
  };
  private boolean[] selectedMuscleGroups;
  private ArrayList<Integer> muscleGroupList;
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

    this.deleteTrainingDayButton = this.view.findViewById(R.id.delete_training_day);

    loadTrainingDayData();
    addSaveButtonListener();
    setupMuscleGroupViewer();
    addArchiveFunction();

    return view;
  }

  /** Add functionality to archive button so exp can be gained by archiving a training day. */
  private void addArchiveFunction() {
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

              changeExperience(true);
            }
          });
    }
  }

  /** initialize the muscle group viewer with muscle groups from storage. */
  private void setupMuscleGroupViewer() {
    this.muscleGroupView = view.findViewById(R.id.muscle_group_view);
    this.selectedMuscleGroups = new boolean[muscleGroupArray.length];
    this.muscleGroupList = new ArrayList<>();

    final Date trainingDayDate = DateConverter.parseStringToDate(dateTextView.getText().toString());

    final List<TrainingDayWithMuscleGroups> trainingDayWithMuscleGroups =
        DatabaseManager.appDatabase
            .trainingDayDao()
            .getTrainingDaysWithMuscleGroupsByDate(trainingDayDate);

    if (trainingDayWithMuscleGroups.size() != 0) {
      final List<MuscleGroup> storedMuscleGroupData =
          trainingDayWithMuscleGroups.get(0).muscleGroupList;

      final StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < storedMuscleGroupData.size(); i++) {
        final int muscleGroupId = storedMuscleGroupData.get(i).muscleGroupId;
        final String muscleGroupName = storedMuscleGroupData.get(i).getMuscleGroupName();

        this.selectedMuscleGroups[muscleGroupId] = true;
        this.muscleGroupList.add(muscleGroupId);

        stringBuilder.append(muscleGroupName);
        if (i != storedMuscleGroupData.size() - 1) stringBuilder.append(", ");
      }
      this.muscleGroupView.setText(stringBuilder.toString());
    }

    setViewerDialogActions();
  }

  /** Add dialogue actions for the muscle group viewer. */
  private void setViewerDialogActions() {
    this.muscleGroupView.setOnClickListener(
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
                    final StringBuilder stringBuilder = new StringBuilder();
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
  }

  /**
   * Remove or add experience.
   *
   * @param addExperience if true add if false subtract.
   */
  private void changeExperience(final boolean addExperience) {
    final Serializer serializer = new Serializer();
    final ExperienceBar experienceBar =
        (ExperienceBar)
            serializer.deserialize(
                getActivity(), ExperienceBar.class, Savefile.EXPERIENCE_BAR_SAVEFILE);

    if (addExperience) {
      experienceBar.addExperience(40);
    } else experienceBar.subtractExperience(40);

    serializer.serialize(getActivity(), experienceBar, Savefile.EXPERIENCE_BAR_SAVEFILE);
  }

  private void grayOutImageButton(final ImageButton imageButton) {
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

    final Date trainingDayDate = DateConverter.parseStringToDate(dateString);

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
  private void setupTrainingPlanFragment(final TrainingDay trainingDay) {
    final FragmentManager fragmentManager = getChildFragmentManager();
    final List<Fragment> fragmentList = fragmentManager.getFragments();

    final Bundle bundle = new Bundle();
    bundle.putInt("trainingPlanId", trainingDay.getTrainingPlanId());
    bundle.putInt("actionId", 1);

    fragmentList.get(0).setArguments(bundle);
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

  private void revertGrayOutForDeleteButton(final Date trainingDayDate) {

    this.deleteTrainingDayButton.setAlpha(1f);
    this.deleteTrainingDayButton.setClickable(true);
    this.deleteTrainingDayButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DatabaseManager.appDatabase.trainingDayDao().deleteById(trainingDayDate);
            DatabaseManager.appDatabase
                .trainingDayMuscleGroupCrossRefDao()
                .deleteAllByDate(trainingDayDate);
            changeExperience(false);
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
