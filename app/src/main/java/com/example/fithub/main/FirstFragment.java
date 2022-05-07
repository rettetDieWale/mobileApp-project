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

    view.findViewById(R.id.stored_data);

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

    final Button storeButton = view.findViewById(R.id.button_save);
    storeButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {}
        });
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /** initializes the experience bar with saved values. */
  public void initExperienceBar() {
    Serializer serializer = new Serializer();
    ExperienceBar expBar =
        (ExperienceBar) serializer.deserialize(getActivity(), ExperienceBar.class, "Demo.txt");

    progressBar.setMax(expBar.getMax());
    progressBar.setProgress(expBar.getProgress());

    levelLabel.setText("Level " + expBar.getLevel());
    progressLabel.setText(expBar.getProgress() + "/" + expBar.getMAX_EXPERIENCE());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
