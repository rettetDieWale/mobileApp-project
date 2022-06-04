package com.example.fithub.main.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingDayBinding;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrainingDayFragment extends Fragment {

  private FragmentTrainingDayBinding binding;

  private View view;
  private TextView dateTextView;
  private EditText wellBeingView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_training_day, container, false);

    this.wellBeingView = this.view.findViewById(R.id.well_being_value);

    final FragmentManager fragmentManager = getChildFragmentManager();
    final List<Fragment> fragmentList = fragmentManager.getFragments();

    final Bundle b = new Bundle();
    b.putInt("trainingPlanId", 1);
    b.putInt("actionId", 1);
    fragmentList.get(0).setArguments(b);

    setDate();
    final Date date = parseDateString(dateTextView.getText().toString());
    if (date == null) {
      // TODO::
    }
    final int wellBeing = Integer.parseInt(wellBeingView.getText().toString());

    DatabaseManager.appDatabase
        .trainingDayDao()
        .insert(
            new TrainingDay(0, parseDateString(dateTextView.getText().toString()), 1, wellBeing));

    final Button saveButton = this.view.findViewById(R.id.save_training_day);
    saveButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            DatabaseManager.appDatabase
                .trainingDayDao()
                .update(
                    new TrainingDay(
                        0, parseDateString(dateTextView.getText().toString()), 1, wellBeing));
          }
        });

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  private Date parseDateString(final String dateString) {
    Date date = null;
    try {
      final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      date = dateFormat.parse(dateString);
    } catch (ParseException parseException) {

    }

    return date;
  }

  private void setDate() {
    Bundle bundle = getArguments();
    String dateString = null;
    if (bundle != null) {
      dateString = (String) bundle.getSerializable("date");
    }
    this.dateTextView = this.view.findViewById(R.id.dateText);
    this.dateTextView.setText(dateString);
  }
}
