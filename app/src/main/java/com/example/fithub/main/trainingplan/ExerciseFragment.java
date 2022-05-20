package com.example.fithub.main.trainingplan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Switch;
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
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class ExerciseFragment extends Fragment {
  TextView InstructionTextArea, exerciseTitle;
  Serializer serializer;

  private View view;
  private ExerciseData exerciseData;
  // image and video components don't have url attributes for temporary storage
  private String tempImageUrl, tempVideoUrl;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_exercise, container, false);

    final Bundle bundle = getArguments();
    this.exerciseData = (ExerciseData) bundle.getSerializable("exercise");
    setExerciseContent(exerciseData);

    this.tempImageUrl = this.exerciseData.getImageUrl();
    this.tempVideoUrl = this.exerciseData.getVideoUrl();

    configureTextViewSwitcher(
        R.id.viewSwitcherTitle, R.id.exercise_name, R.id.editTextInputName, R.id.submitButtonName);

    configureTextViewSwitcher(
        R.id.viewSwitcherInstruction,
        R.id.exercise_text_area,
        R.id.editTextInputInstruction,
        R.id.submitButtonInstruction);

    final ViewSwitcher imageViewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcherImage);
    final ImageView imageView = (ImageView) view.findViewById(R.id.exercise_image);
    final EditText imageEditText = (EditText) view.findViewById(R.id.editTextInputImage);

    final Button submitButtonImage = (Button) view.findViewById(R.id.submitButtonImage);

    imageView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            imageViewSwitcher.showNext();
            imageEditText.setText(tempImageUrl, TextView.BufferType.EDITABLE);
          }
        });

    submitButtonImage.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            imageViewSwitcher.showNext();
            loadExerciseImage(imageEditText.getText().toString());
          }
        });

    final ViewSwitcher videoViewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcherVideo);
    final WebView videoWebView = (WebView) view.findViewById(R.id.exercise_webview);
    final Switch videoSwitch = (Switch) view.findViewById(R.id.switchVideo);
    final EditText videoEditText = (EditText) view.findViewById(R.id.editTextInputVideo);
    final Button submitButtonVideo = (Button) view.findViewById(R.id.submitButtonVideo);

    videoSwitch.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            videoWebView.setVisibility(View.GONE);
            videoViewSwitcher.showNext();
            videoEditText.setText(tempVideoUrl, TextView.BufferType.EDITABLE);
          }
        });

    submitButtonVideo.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            videoWebView.setVisibility(View.VISIBLE);
            videoViewSwitcher.showNext();
            loadExerciseVideo(videoEditText.getText().toString());
          }
        });

    return view;
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

    loadExerciseImage(exerciseData.getImageUrl());
    loadExerciseVideo(exerciseData.getVideoUrl());

    this.InstructionTextArea = this.view.findViewById(R.id.exercise_text_area);
    this.InstructionTextArea.setText(exerciseData.getInstruction());

    this.exerciseTitle = this.view.findViewById(R.id.exercise_name);
    this.exerciseTitle.setText(exerciseData.getName());
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

  public Bitmap LoadImageFromWebOperations(String url) {
    try {
      InputStream is = (InputStream) new URL(url).getContent();
      Bitmap bitmap = BitmapFactory.decodeStream(is);
      return bitmap;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
