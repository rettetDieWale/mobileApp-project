package com.example.fithub.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentFirstBinding;
import com.example.fithub.main.calendar.CalendarActivity;
import com.example.fithub.main.prototypes.ExperienceBar;
import com.example.fithub.main.storage.Serializer;

public class FirstFragment extends Fragment {

  private FragmentFirstBinding binding;
  private ProgressBar progressBar;
  private TextView levelLabel, progressLabel;
  private ExperienceBar experienceBar;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_first, container, false);
    // binding = FragmentFirstBinding.inflate(inflater, container, false);
    initComponents(view);
    return view;
  }

  /**
   * Initialize components and add them to the fragment so they can be accessed in code.
   *
   * @param view the components where added into layout xml
   */
  public final void initComponents(View view) {

    this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    this.levelLabel = (TextView) view.findViewById(R.id.text_view_level);
    this.progressLabel = (TextView) view.findViewById(R.id.text_view_progress);

    createOnClickListeners(view);

    initExperienceBar();
  }

  /**
   * Creates onClick listeners for buttons and initializes them.
   *
   * @param view the buttons where added into layout xml
   */
  public final void createOnClickListeners(View view) {

    // Buttons are not initialized in initComponents() to reduce redundant code
    final Button calendarButton = (Button) view.findViewById(R.id.button_home);
    calendarButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    final Button addExperienceButton = (Button) view.findViewById(R.id.button_add_experience);
    addExperienceButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            experienceBar.addExperience(30);
            updateExperienceBar();
          }
        });
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /** initializes the experience bar with saved values. */
  public void initExperienceBar() {
    Serializer serializer = new Serializer();
    experienceBar =
        (ExperienceBar) serializer.deserialize(getActivity(), ExperienceBar.class, "Demo.json");

    // if file cant be serialized from a new exp bar needs to be created
    if (experienceBar == null) {
      resetExperienceBar();
    }
    updateExperienceBar();
  }

  /**
   * reset experience bar values. Useful when app is started the first time or save files have been
   * deleted or corrupted.
   */
  public void resetExperienceBar() {
    experienceBar = new ExperienceBar(100, 0, 0);
  }

  /**
   * Updates the experience bar graphic component. Should be called after values have been changed.
   */
  public void updateExperienceBar() {
    progressBar.setMax(experienceBar.getMax());
    progressBar.setProgress(experienceBar.getProgress());

    levelLabel.setText("Level " + experienceBar.getLevel());
    progressLabel.setText(experienceBar.getProgress() + "/" + experienceBar.getMAX_EXPERIENCE());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
