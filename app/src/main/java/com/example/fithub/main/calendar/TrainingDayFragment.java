package com.example.fithub.main.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingDayBinding;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingDay;

import java.util.Date;
import java.util.List;

public class TrainingDayFragment extends Fragment {

  private FragmentTrainingDayBinding binding;

  private View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_training_day, container, false);

    // ToDo:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    DatabaseManager.appDatabase.trainingDayDao().insert(new TrainingDay(0, new Date(), 1, 1));
    // -------------------------------------------------------------
    final FragmentManager fragmentManager = getChildFragmentManager();
    final List<Fragment> fragmentList = fragmentManager.getFragments();

    final Bundle b = new Bundle();
    b.putInt("trainingPlanId", 1);
    b.putInt("actionId", 1);
    fragmentList.get(0).setArguments(b);

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    setDate();

    super.onViewCreated(view, savedInstanceState);
  }

  private void setDate() {
    Bundle bundle = getArguments();
    String date = null;
    if (bundle != null) {
      date = (String) bundle.getSerializable("date");
    }
    TextView tv = (TextView) getView().findViewById(R.id.dateText);
    tv.setText(date);
  }
}
