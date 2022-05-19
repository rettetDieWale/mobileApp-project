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
import com.example.fithub.databinding.FragmentExerciseBinding;
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

  private FragmentExerciseBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_exercise, container, false);
    final Bundle bundle = getArguments();
    final ExerciseData exerciseData = (ExerciseData) bundle.getSerializable("exercise");

    final ViewSwitcher viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
    final TextView exerciseNameTextView = (TextView) view.findViewById(R.id.exercise_name);
    final EditText exerciseEditText = (EditText) view.findViewById(R.id.editTextInput);

    exerciseNameTextView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            viewSwitcher.showNext();
            exerciseEditText.setText(exerciseNameTextView.getText(), TextView.BufferType.EDITABLE);
          }
        });

    final Button exerciseTextSubmit = (Button) view.findViewById(R.id.submit);
    exerciseTextSubmit.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            exerciseNameTextView.setText(exerciseEditText.getText());
            viewSwitcher.showNext();
          }
        });

    setExerciseContent(view, exerciseData);

    return view;
  }

  /**
   * Load content from storage into fragment content.
   *
   * @param view of the fragment.
   */
  public void loadExerciseContent(View view) {
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
    setExerciseContent(view, exerciseData);

    this.serializer.serialize(getActivity(), exerciseDataTemplates, Savefile.EXERCISE_SAVEFILE);
  }

  /**
   * Set the attributes of a exercise for the connected layout components.
   *
   * @param view of the fragment the components belong to
   * @param exerciseData from storage whom data should be used
   */
  public void setExerciseContent(View view, ExerciseData exerciseData) {

    loadExerciseImage(view, exerciseData.getImageId());
    loadExerciseVideo(view, exerciseData.getVideoUrl());

    this.InstructionTextArea = view.findViewById(R.id.exercise_text_area);
    this.InstructionTextArea.setText(exerciseData.getInstruction());

    this.exerciseTitle = view.findViewById(R.id.exercise_name);
    this.exerciseTitle.setText(exerciseData.getName());
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
