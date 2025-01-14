package com.example.fithub.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.ExperienceBar;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;
import com.example.fithub.main.prototypes.data.TrainingPlan;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type First fragment.
 */
public class FirstFragment extends Fragment {

  private ProgressBar progressBar;
  private TextView levelLabel, progressLabel;
  private ExperienceBar experienceBar;
  private TrainingDay nextTrainingDay;
  private View view;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.view = inflater.inflate(R.layout.fragment_first, container, false);

    DatabaseManager.initDatabase(getActivity());
    // For Test Purpose only: use this to clear database and load templates new
    // DatabaseManager.addTemplates(getActivity());

    checkForFirstStartup();
    setNextTrainingDayView();
    initComponents();

    return view;
  }

  /** Sets the preview of the next training day from storage. */
  private void setNextTrainingDayView() {
    this.nextTrainingDay =
        DatabaseManager.appDatabase.trainingDayDao().getNextTrainingDay(getToday());

    if (this.nextTrainingDay != null) {
      final TextView dateText = this.view.findViewById(R.id.next_date_textview);
      final TextView amountExercises = this.view.findViewById(R.id.number_exercises_textview);

      final String date = new SimpleDateFormat("dd MMMM").format(nextTrainingDay.getDate());
      dateText.setText(date.toString().replace(" ", "\n"));

      final TrainingPlan trainingPlan =
          DatabaseManager.appDatabase
              .trainingPlanDao()
              .getById(nextTrainingDay.getTrainingPlanId());

      final List<PlanEntry> planEntries =
          DatabaseManager.appDatabase
              .planEntryDao()
              .getPlanEntryListByPlanId(trainingPlan.getTrainingPlanId());

      final String exerciseNumber = Integer.toString(planEntries.size());
      amountExercises.setText(exerciseNumber + " Übungen");

      checkForMuscleRepetition(this.nextTrainingDay);
    }
  }

  /**
   * Check if the apps has been started before and if not init templates and setup shared
   * preferences.
   */
  private void checkForFirstStartup() {
    final SharedPreferences preferences =
        PreferenceManager.getDefaultSharedPreferences(getActivity());
    if (!preferences.getBoolean("firstTime", false)) {
      DatabaseManager.addTemplates(getActivity());
      Toast.makeText(getActivity(), " Wilkommen bei Fithub!", Toast.LENGTH_SHORT).show();

      final SharedPreferences.Editor editor = preferences.edit();
      editor.putBoolean("firstTime", true);
      editor.commit();
    }
  }

  /** Initialize components and add them to the fragment so they can be accessed in code. */
  private void initComponents() {

    this.progressBar = (ProgressBar) this.view.findViewById(R.id.progressBar);
    this.levelLabel = (TextView) this.view.findViewById(R.id.text_view_level);
    this.progressLabel = (TextView) this.view.findViewById(R.id.text_view_progress);

    createOnClickListeners();
    initExperienceBar();
  }

  /**
   * Check if the next training day muscle groups are conflicting with the workouts in the last 48h
   *
   * @param nextTrainingDay to be checked
   */
  private void checkForMuscleRepetition(final TrainingDay nextTrainingDay) {
    final List<TrainingDayMuscleGroupCrossRef> nextTrainingDayMuscleGroups =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .getByDate(nextTrainingDay.getDate());

    if (nextTrainingDay == null) return;

    final int MUSCLE_SORENESS_DURATION = 2;

    final Date nextTrainingDate = nextTrainingDay.getDate();
    final Calendar calendar = Calendar.getInstance();

    calendar.setTime(nextTrainingDate);
    calendar.add(Calendar.DATE, -MUSCLE_SORENESS_DURATION);
    Date lowerDateInterval = calendar.getTime();

    calendar.add(Calendar.DATE, 1);
    Date upperDateInterval = calendar.getTime();

    final List<TrainingDayMuscleGroupCrossRef> lastTrainingDaysMuscleGroups =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .getInterval(lowerDateInterval, upperDateInterval);

    // use set to filter out duplicates
    final Set<String> stringSet = new HashSet<>();

    boolean conflictFound = false;

    for (int i = 0; i < nextTrainingDayMuscleGroups.size(); i++) {
      for (int j = 0; j < lastTrainingDaysMuscleGroups.size(); j++) {

        if (nextTrainingDayMuscleGroups.get(i).getMuscleGroupId()
            == lastTrainingDaysMuscleGroups.get(j).getMuscleGroupId()) {
          conflictFound = true;
          final MuscleGroup muscleGroup =
              DatabaseManager.appDatabase
                  .muscleGroupDao()
                  .getById(nextTrainingDayMuscleGroups.get(i).muscleGroupId);
          stringSet.add(muscleGroup.muscleGroupName);
        }
      }
    }

    if (conflictFound) {
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("WARNUNG: die Muskelgruppen: \n");
      stringBuilder.append(stringSet);
      stringBuilder.append("\nbereits in den letzten 48h trainiert");

      String overtrainedMessage = stringBuilder.toString();
      overtrainedMessage = overtrainedMessage.replace("[", "");
      overtrainedMessage = overtrainedMessage.replace("]", " ");

      Toast.makeText(getActivity(), overtrainedMessage, Toast.LENGTH_LONG).show();
    }
  }

  /** Creates onClick listeners for buttons and initializes them. */
  private void createOnClickListeners() {

    // Buttons are not initialized in initComponents() to reduce redundant code
    final ImageButton calendarButton = this.view.findViewById(R.id.button_calendar);
    calendarButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_calenderOverviewFragment);
          }
        });

    if (nextTrainingDay != null) {
      final ImageButton imageButton = view.findViewById(R.id.imageButton);
      imageButton.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Bundle args = new Bundle();
              args.putSerializable("date", nextTrainingDay.getDate());
              NavHostFragment.findNavController(FirstFragment.this)
                  .navigate(R.id.action_FirstFragment_to_trainingDayFragment, args);
            }
          });
    }

    final ImageButton analysisButton = this.view.findViewById(R.id.button_analysis);
    analysisButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_statisticFragment);
          }
        });

    final ImageButton trainingPlanButton = this.view.findViewById(R.id.training_plans_button);
    trainingPlanButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_trainingPlanOverviewFragment);
          }
        });
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /** initializes the experience bar with saved values. */
  private void initExperienceBar() {
    final Serializer serializer = new Serializer();
    this.experienceBar =
        (ExperienceBar)
            serializer.deserialize(
                getActivity(), ExperienceBar.class, Savefile.EXPERIENCE_BAR_SAVEFILE);

    // if file cant be serialized from a new exp bar needs to be created
    if (this.experienceBar == null) {
      resetExperienceBar();
    }
    updateExperienceBar();
  }

  /**
   * reset experience bar values. Useful when app is started the first time or save files have been
   * deleted or corrupted.
   */
  private void resetExperienceBar() {
    this.experienceBar = new ExperienceBar(100, 0, 0);
  }

  /**
   * Updates the experience bar graphic component. Should be called after values have been changed.
   */
  private void updateExperienceBar() {
    this.progressBar.setMax(this.experienceBar.getMax());

    this.levelLabel.setText("Level " + this.experienceBar.getLevel());
    this.progressLabel.setText(
        this.experienceBar.getProgress() + "/" + this.experienceBar.getMAX_EXPERIENCE());

    this.progressBar.post(
        new Runnable() {
          @Override
          public void run() {
            progressBar.setProgress(experienceBar.getProgress());
          }
        });

    Serializer serializer = new Serializer();
    serializer.serialize(getActivity(), this.experienceBar, Savefile.EXPERIENCE_BAR_SAVEFILE);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  private Date getToday() {
    final Calendar calendar = new GregorianCalendar();

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }
}
