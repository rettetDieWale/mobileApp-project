package com.example.fithub.main.trainingplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentExerciseBinding;
import com.example.fithub.main.components.TemplateSpinner;
import com.example.fithub.main.prototypes.Exercise;
import com.example.fithub.main.prototypes.Templates;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExerciseFragment extends Fragment {
  TextView InstructionTextArea, exerciseTitle;
  Serializer serializer;

  private FragmentExerciseBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_exercise, container, false);

    initSpinner(view);
    loadExerciseContent(view);

    return view;
  }

  /**
   * Initializes a spinner with a list of templates.
   *
   * @param view the spinner is attached to in layout file
   */
  public void initSpinner(View view) {
    final TemplateSpinner spinner = new TemplateSpinner(view, getActivity(), R.id.spinner_exercise);
  }

  /**
   * Load content from storage into fragment content.
   *
   * @param view of the fragment.
   */
  public void loadExerciseContent(View view) {
    this.serializer = new Serializer();
    Type listOfExercisesType = new TypeToken<List<Exercise>>() {}.getType();

    List<Exercise> exerciseTemplates =
        (List<Exercise>)
            this.serializer.deserialize(
                getActivity(), listOfExercisesType, Savefile.EXERCISE_SAVEFILE);

    // Templates need to be created if file is corrupted or not existent
    if (exerciseTemplates == null) {
      Templates templates = new Templates();
      exerciseTemplates = templates.createExerciseTemplates();
    }

    Exercise exercise = exerciseTemplates.get(0);
    setExerciseContent(view, exercise);
  }

  /**
   * Set the attributes of a exercise for the connected layout components.
   *
   * @param view of the fragment the components belong to
   * @param exercise from storage whom data should be used
   */
  public void setExerciseContent(View view, Exercise exercise) {
    loadExerciseImage(view, exercise.getImageId());
    loadExerciseVideo(view, exercise.getVideoUrl());

    this.InstructionTextArea = view.findViewById(R.id.exercise_text_area);
    this.InstructionTextArea.setText(exercise.getInstruction());

    this.exerciseTitle = view.findViewById(R.id.exercise_name);
    this.exerciseTitle.setText(exercise.getName());
  }

  /**
   * Load an image from the drawable folder into the exercise image view.
   *
   * @param view the image is attached to
   * @param imageId R.drawable.imageID path
   */
  public void loadExerciseImage(View view, int imageId) {
    final ImageView imageView = view.findViewById(R.id.exercise_image);
    imageView.setImageResource(imageId);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  /**
   * Load an video inside the webview that is located in the exercise fragment from youtube.
   *
   * @param view the video is attached to
   */
  public void loadExerciseVideo(View view, String frameVideo) {

    WebView displayYoutubeVideo = (WebView) view.findViewById(R.id.exercise_webview);
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
    binding = null;
  }
}
