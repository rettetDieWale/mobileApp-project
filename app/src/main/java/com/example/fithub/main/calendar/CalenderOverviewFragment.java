package com.example.fithub.main.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderOverviewFragment extends Fragment {

  private CalendarView simpleCalendarView;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_calender_overview, container, false);

    CompactCalendarView compactCalendarView = view.findViewById(R.id.compactcalendar_view);
    compactCalendarView.setUseThreeLetterAbbreviation(true);
    TextView currentMonthTextView = view.findViewById(R.id.month);
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
    String month_name = month_date.format(cal.getTime());

    currentMonthTextView.setText(month_name);

    // set events
    List<TrainingDay> trainingDays = DatabaseManager.appDatabase.trainingDayDao().getAll();

    for (int i = 0; i < trainingDays.size(); i++) {
      Date trainingDayDate = trainingDays.get(i).getDate();

      Event event = new Event(Color.WHITE, trainingDayDate.getTime(), "training_day");
      compactCalendarView.addEvent(event, true);
    }

    compactCalendarView.setListener(
        new CompactCalendarView.CompactCalendarViewListener() {
          @Override
          public void onDayClick(Date dateClicked) {
            Bundle args = new Bundle();
            args.putSerializable("date", dateClicked);
            NavHostFragment.findNavController(CalenderOverviewFragment.this)
                .navigate(R.id.action_calenderOverviewFragment_to_trainingDayFragment, args);
          }

          @Override
          public void onMonthScroll(Date firstDayOfNewMonth) {
            TextView currentMonthTextView = view.findViewById(R.id.month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(compactCalendarView.getFirstDayOfCurrentMonth());
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            String month_name = month_date.format(cal.getTime());
            currentMonthTextView.setText(month_name);
          }
        });

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
