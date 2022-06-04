package com.example.fithub.main.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

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
import java.util.Date;
import java.util.Locale;

public class CalenderOverviewFragment extends Fragment {

  private CalendarView simpleCalendarView;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_calender_overview, container, false);

    // TODO: Code NOCH VERSCHIEBEN

    TrainingDay trainingDay =
        DatabaseManager.appDatabase
            .trainingDayDao()
            .getByDate(DateConverter.parseStringToDate("03-06-2022"));

    /// TEST CALENDAR TODO:

    CompactCalendarView compactCalendarView = view.findViewById(R.id.compactcalendar_view);
    compactCalendarView.setUseThreeLetterAbbreviation(true);

    final SimpleDateFormat simpleDateFormat =
        new SimpleDateFormat("dd-MM-yyyyy", Locale.getDefault());

    final Date date = DateConverter.parseStringToDate("01-06-2022");

    // set event
    Event event = new Event(Color.BLUE, date.getTime(), "Description");
    compactCalendarView.addEvent(event, true);

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
          public void onMonthScroll(Date firstDayOfNewMonth) {}
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
