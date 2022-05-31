package com.example.fithub.main.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fithub.R;
import com.example.fithub.main.MainActivity;

public class CalenderOverviewFragment extends Fragment {

  private CalendarView simpleCalendarView;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_calender_overview, container, false);
    initComponents(view);

    // TODO: Code NOCH VERSCHIEBEN
    // super.onCreate(savedInstanceState);
    // setContentView(R.layout.newact);
    CalendarView calendarView = (CalendarView) view.findViewById(R.id.simpleCalendarView);
    calendarView.setOnDateChangeListener(
        new CalendarView.OnDateChangeListener() {

          @Override
          public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            Bundle args = new Bundle();
            month = month + 1;
            String date = dayOfMonth + "/" + month + "/" + year;
            args.putSerializable("date", date);

            NavHostFragment.findNavController(CalenderOverviewFragment.this)
                .navigate(R.id.action_calenderOverviewFragment_to_trainingDayFragment, args);
          }
        });

    return view;
  }

  /**
   * Initialize components and add them to the fragment so they can be accessed in code.
   *
   * @param view the components where added into layout xml
   */
  private void initComponents(View view) {

    this.simpleCalendarView = (CalendarView) view.findViewById(R.id.simpleCalendarView);
    createOnClickListeners(view);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /**
   * Creates onClick listeners for buttons and initializes them.
   *
   * @param view the buttons where added into layout xml
   */
  private void createOnClickListeners(View view) {

    final Button back = (Button) view.findViewById(R.id.button_calender_return);
    back.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
          }
        });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
