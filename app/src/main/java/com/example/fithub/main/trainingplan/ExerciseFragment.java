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
import com.example.fithub.main.components.TemplateSpinner;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.ExerciseData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends Fragment {
  private TextView InstructionTextArea, exerciseTitle;

  private View view;
  private ExerciseData exerciseData;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_exercise, container, false);

    final Bundle bundle = getArguments();
    final int exerciseDataId = bundle.getInt("exerciseDataId");

    this.exerciseData =
        DatabaseManager.appDatabase.exerciseDataDao().getExerciseData(exerciseDataId);

    setExerciseContent(exerciseData);

    configureTextViewSwitcher(
        R.id.viewSwitcherTitle, R.id.exercise_name, R.id.editTextInputName, R.id.submitButtonName);

    configureTextViewSwitcher(
        R.id.viewSwitcherInstruction,
        R.id.exercise_text_area,
        R.id.editTextInputInstruction,
        R.id.submitButtonInstruction);

    setImageViewSwitcher();
    setVideoViewSwitcher();

    final Button saveExerciseDataButton = (Button) view.findViewById(R.id.button_save_exercise);
    saveExerciseDataButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            updateExerciseData();
          }
        });

    initSpinner();

    return view;
  }

  /** Make image clickable so url for exercise image can be manually changed. */
  public void setImageViewSwitcher() {
    final ViewSwitcher imageViewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcherImage);
    final ImageView imageView = (ImageView) view.findViewById(R.id.exercise_image);
    final EditText imageEditText = (EditText) view.findViewById(R.id.editTextInputImage);

    final Button submitButtonImage = (Button) view.findViewById(R.id.submitButtonImage);

    imageView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            imageViewSwitcher.showNext();
            imageEditText.setText(exerciseData.getImageUrl(), TextView.BufferType.EDITABLE);
          }
        });

    submitButtonImage.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            imageViewSwitcher.showNext();
            loadExerciseImage(imageEditText.getText().toString());
            exerciseData.setImageUrl(imageEditText.getText().toString());
          }
        });
  }

  /** make url under video clickable to it can be edited and changed manually. */
  public void setVideoViewSwitcher() {
    final ViewSwitcher videoViewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcherVideo);
    final WebView videoWebView = (WebView) view.findViewById(R.id.exercise_webview);
    final TextView videoTextView = (TextView) view.findViewById(R.id.videoUrl_textView);
    videoTextView.setText(exerciseData.getVideoUrl());
    final EditText videoEditText = (EditText) view.findViewById(R.id.editTextInputVideo);
    final Button submitButtonVideo = (Button) view.findViewById(R.id.submitButtonVideo);

    videoTextView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            videoWebView.setVisibility(View.GONE);
            videoViewSwitcher.showNext();
            videoEditText.setText(exerciseData.getVideoUrl(), TextView.BufferType.EDITABLE);
          }
        });

    submitButtonVideo.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            videoWebView.setVisibility(View.VISIBLE);
            videoViewSwitcher.showNext();
            loadExerciseVideo(videoEditText.getText().toString());
            exerciseData.setVideoUrl(videoEditText.getText().toString());
            videoTextView.setText(exerciseData.getVideoUrl());
          }
        });
  }

  /**
   * Makes the text view clickable and opens a input to change the value.
   *
   * @param viewSwitcherId id of the view switcher container
   * @param textViewId id for the text view that should change on click
   * @param editTextId id for input field that pops up on click
   * @param submitButtonId id for the button to submit input and switch back to textview
   */
  public void configureTextViewSwitcher(
      int viewSwitcherId, int textViewId, int editTextId, int submitButtonId) {

    final ViewSwitcher viewSwitcher = (ViewSwitcher) view.findViewById(viewSwitcherId);
    final TextView textView = (TextView) view.findViewById(textViewId);
    final EditText editText = (EditText) view.findViewById(editTextId);

    textView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            viewSwitcher.showNext();
            editText.setText(textView.getText(), TextView.BufferType.EDITABLE);
          }
        });

    final Button submitButton = (Button) view.findViewById(submitButtonId);
    submitButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            textView.setText(editText.getText());
            viewSwitcher.showNext();
          }
        });
  }

  /**
   * Set the attributes of a exercise for the connected layout components.
   *
   * @param exerciseData from storage which data should be used
   */
  public void setExerciseContent(ExerciseData exerciseData) {

    loadExerciseImage(exerciseData.getImageUrl());
    loadExerciseVideo(exerciseData.getVideoUrl());

    this.InstructionTextArea = this.view.findViewById(R.id.exercise_text_area);
    this.InstructionTextArea.setText(exerciseData.getInstruction());

    this.exerciseTitle = this.view.findViewById(R.id.exercise_name);
    this.exerciseTitle.setText(exerciseData.getName());
  }

  /** */
  public void updateExerciseData() {
    this.exerciseData.setName(exerciseTitle.getText().toString());
    this.exerciseData.setInstruction(InstructionTextArea.getText().toString());

    DatabaseManager.appDatabase.exerciseDataDao().update(this.exerciseData);
  }

  /**
   * Load an image from a web source into the exercise image view.
   *
   * @param url to web image (should be https for picasso)
   */
  public void loadExerciseImage(String url) {

    final ImageView imageView = this.view.findViewById(R.id.exercise_image);
    Picasso.get().load(url).placeholder(R.drawable.klimmzug).into(imageView);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  /** Load an video inside the webview that is located in the exercise fragment from youtube. */
  public void loadExerciseVideo(String url) {

    String frameVideo = parseVideoUrl(url);
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

  /**
   * Changes the url from user input into the needed iframe format for a webView.
   *
   * @param url given from user generally in format like: https://youtu.be/LnhpKTXeIeg
   * @return full iframe url that can be put into a WebView component
   */
  public String parseVideoUrl(String url) {
    String[] separatedUrl = url.split("/");
    String youtubeId = separatedUrl[separatedUrl.length - 1];
    String fullUrl =
        "<html><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/"
            + youtubeId
            + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
    return fullUrl;
  }

  /** Initializes a spinner . */
  public void initSpinner() {
    final List<ExerciseData> exerciseDataList =
        DatabaseManager.appDatabase.exerciseDataDao().getAll();

    final ArrayList<String> exerciseDataNames = new ArrayList<>();
    for (int i = 0; i < exerciseDataList.size(); i++) {
      exerciseDataNames.add(exerciseDataList.get(i).getName());
    }

    final TemplateSpinner spinner =
        new TemplateSpinner(view, getActivity(), R.id.exercise_spinner, exerciseDataNames);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
