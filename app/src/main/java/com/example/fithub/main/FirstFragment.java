package com.example.fithub.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.main.calendar.CalendarActivity;
import com.example.fithub.main.prototypes.ExperienceBar;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;

public class FirstFragment extends Fragment {

  private ProgressBar progressBar;
  private TextView levelLabel, progressLabel;
  private ExperienceBar experienceBar;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // TODO: only for test purpose
    DatabaseManager.initDatabase(getActivity());
    DatabaseManager.addTemplates(getActivity());

    final View view = inflater.inflate(R.layout.fragment_first, container, false);
    initComponents(view);
    return view;
  }

  /**
   * Initialize components and add them to the fragment so they can be accessed in code.
   *
   * @param view the components where added into layout xml
   */
  private void initComponents(View view) {

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
  private void createOnClickListeners(View view) {

    // Buttons are not initialized in initComponents() to reduce redundant code
    final ImageButton calendarButton = view.findViewById(R.id.button_calendar);
    calendarButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
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
    this.progressBar.setProgress(this.experienceBar.getProgress());

    this.levelLabel.setText("Level " + this.experienceBar.getLevel());
    this.progressLabel.setText(
        this.experienceBar.getProgress() + "/" + this.experienceBar.getMAX_EXPERIENCE());

    Serializer serializer = new Serializer();
    serializer.serialize(getActivity(), this.experienceBar, Savefile.EXPERIENCE_BAR_SAVEFILE);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
