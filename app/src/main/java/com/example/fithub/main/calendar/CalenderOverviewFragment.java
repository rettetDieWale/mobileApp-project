package com.example.fithub.main.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/** The type Calender overview fragment. */
public class CalenderOverviewFragment extends Fragment {

  private CompactCalendarView compactCalendarView;
  private View view;
  private Calendar calendar;
  private SimpleDateFormat monthDateFormat;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    this.view = inflater.inflate(R.layout.fragment_calender_overview, container, false);

    this.compactCalendarView = view.findViewById(R.id.compactcalendar_view);
    this.compactCalendarView.setUseThreeLetterAbbreviation(true);

    this.calendar = Calendar.getInstance();
    this.monthDateFormat = new SimpleDateFormat("MMMM");

    setMonthView();
    setCalenderEvents();

    setCalenderListeners();

    return view;
  }

  /** Add onClick and onMonthScroll Even listener actions for the calendar. */
  private void setCalenderListeners() {
    this.compactCalendarView.setListener(
        new CompactCalendarView.CompactCalendarViewListener() {
          @Override
          public void onDayClick(Date dateClicked) {
            navigateToTrainingDay(dateClicked);
          }

          @Override
          public void onMonthScroll(Date firstDayOfNewMonth) {
            calendar.setTime(compactCalendarView.getFirstDayOfCurrentMonth());
            setMonthView();
          }
        });
  }

  /**
   * Navigate to the selected Training Day that got clicked on calendar.
   *
   * @param dateClicked in calendar.
   */
  private void navigateToTrainingDay(final Date dateClicked) {
    final Bundle args = new Bundle();
    args.putSerializable("date", dateClicked);
    NavHostFragment.findNavController(CalenderOverviewFragment.this)
        .navigate(R.id.action_calenderOverviewFragment_to_trainingDayFragment, args);
  }

  /** Get training days from storage and add them to calendar events. */
  private void setCalenderEvents() {
    final List<TrainingDay> trainingDays = DatabaseManager.appDatabase.trainingDayDao().getAll();

    for (int i = 0; i < trainingDays.size(); i++) {
      Date trainingDayDate = trainingDays.get(i).getDate();

      final Event event = new Event(Color.WHITE, trainingDayDate.getTime(), "training_day");
      compactCalendarView.addEvent(event, true);
    }
  }

  /** Set the current month for the text view. */
  private void setMonthView() {
    final TextView currentMonthTextView = view.findViewById(R.id.month);
    final String monthName = monthDateFormat.format(calendar.getTime());

    currentMonthTextView.setText(monthName);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
