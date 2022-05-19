package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.ExerciseData;
import com.example.fithub.main.prototypes.Templates;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExerciseFragment extends Fragment {
  TextView InstructionTextArea, exerciseTitle;
  Serializer serializer;

  private View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_exercise, container, false);
    final Bundle bundle = getArguments();
    final ExerciseData exerciseData = (ExerciseData) bundle.getSerializable("exercise");

    // exercise title input
    final ViewSwitcher viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcherTitle);
    final TextView exerciseNameTextView = (TextView) view.findViewById(R.id.exercise_name);
    final EditText exerciseEditText = (EditText) view.findViewById(R.id.editTextInputName);

    exerciseNameTextView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            viewSwitcher.showNext();
            exerciseEditText.setText(exerciseNameTextView.getText(), TextView.BufferType.EDITABLE);
          }
        });

    final Button exerciseTextSubmit = (Button) view.findViewById(R.id.submitButtonName);
    exerciseTextSubmit.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            exerciseNameTextView.setText(exerciseEditText.getText());
            viewSwitcher.showNext();
          }
        });

    // exercise instruction input

    // exercise title input
    final ViewSwitcher viewSwitcher2 =
        (ViewSwitcher) view.findViewById(R.id.viewSwitcherInstruction);
    final TextView exerciseNameTextView2 = (TextView) view.findViewById(R.id.exercise_text_area);
    final EditText exerciseEditText2 = (EditText) view.findViewById(R.id.editTextInputInstruction);

    exerciseNameTextView2.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            viewSwitcher2.showNext();
            exerciseEditText2.setText(
                exerciseNameTextView2.getText(), TextView.BufferType.EDITABLE);
          }
        });

    final Button exerciseTextSubmit2 = (Button) view.findViewById(R.id.submitButtonInstruction);
    exerciseTextSubmit2.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            exerciseNameTextView2.setText(exerciseEditText2.getText());
            viewSwitcher2.showNext();
          }
        });

    setExerciseContent(exerciseData);

    return view;
  }

  public void configureViewSwitcher() {}

  /** Load content from storage into fragment content. */
  public void loadExerciseContent() {
    this.serializer = new Serializer();
    Type listOfExercisesType = new TypeToken<List<ExerciseData>>() {}.getType();

    List<ExerciseData> exerciseDataTemplates =
        (List<ExerciseData>)
            this.serializer.deserialize(
                getActivity(), listOfExercisesType, Savefile.EXERCISE_SAVEFILE);

    // Templates need to be created if file is corrupted or not existent
    if (exerciseDataTemplates == null) {
      Templates templates = new Templates();
      exerciseDataTemplates = templates.createExerciseTemplates();
    }

    Templates templates = new Templates();
    exerciseDataTemplates = templates.createExerciseTemplates();

    // rendering fragment with basic exercise (empty one) data
    final int standardTemplateNumber = 0;
    final ExerciseData exerciseData = exerciseDataTemplates.get(standardTemplateNumber);
    setExerciseContent(exerciseData);

    this.serializer.serialize(getActivity(), exerciseDataTemplates, Savefile.EXERCISE_SAVEFILE);
  }

  /**
   * Set the attributes of a exercise for the connected layout components.
   *
   * @param exerciseData from storage whom data should be used
   */
  public void setExerciseContent(ExerciseData exerciseData) {

    loadExerciseImage(exerciseData.getImageId());
    loadExerciseVideo(exerciseData.getVideoUrl());

    this.InstructionTextArea = this.view.findViewById(R.id.exercise_text_area);
    this.InstructionTextArea.setText(exerciseData.getInstruction());

    this.exerciseTitle = this.view.findViewById(R.id.exercise_name);
    this.exerciseTitle.setText(exerciseData.getName());
  }

  /**
   * Load an image from the drawable folder into the exercise image view.
   *
   * @param imageId R.drawable.imageID path
   */
  public void loadExerciseImage(int imageId) {
    final ImageView imageView = this.view.findViewById(R.id.exercise_image);
    imageView.setImageResource(imageId);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  /** Load an video inside the webview that is located in the exercise fragment from youtube. */
  public void loadExerciseVideo(String frameVideo) {

    WebView displayYoutubeVideo = (WebView) this.view.findViewById(R.id.exercise_webview);
    displayYoutubeVideo.setWebViewClient(
        new WebViewClient() {
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, String Url) {
            return false;
          }
        });
    WebSettings webSettings = displayYoutubeVideo.getSettings();
    webSettings.setJavaScriptEnabled(true);
    displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
