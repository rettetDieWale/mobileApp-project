package com.example.fithub.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.data.DatabaseManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HumanChartFragment extends Fragment {

  private View view;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.view = inflater.inflate(R.layout.fragment_human_chart, container, false);

    Date[] dates = getTodayAndLimit(7);

    int beine = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(0, dates[0], dates[1]);

    int brust = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(1, dates[0], dates[1]);

    int arme = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(2, dates[0], dates[1]);

    int schultern = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(3, dates[0], dates[1]);

    int bauch = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(4, dates[0], dates[1]);

    int ruecken = DatabaseManager.appDatabase.trainingDayMuscleGroupCrossRefDao().countPastDays(5, dates[0], dates[1]);

    //Rot >= 4
    //gelb = 3
    //grün <= 2
    //weiß = 0
    int farbeArme = checkColor(arme);
    ImageView arm1v = view.findViewById(R.id.arm1);
    ImageView arm2v = view.findViewById(R.id.arm2);
    ImageView arm3v = view.findViewById(R.id.arm3);
    ImageView arm4v = view.findViewById(R.id.arm4);
    arm1v.setColorFilter(farbeArme);
    arm2v.setColorFilter(farbeArme);
    arm3v.setColorFilter(farbeArme);
    arm4v.setColorFilter(farbeArme);

    int farbeBeine = checkColor(beine);
    ImageView bein1v = view.findViewById(R.id.bein1);
    bein1v.setColorFilter(farbeBeine);
    ImageView bein2v = view.findViewById(R.id.bein2);
    bein2v.setColorFilter(farbeBeine);
    ImageView bein3v = view.findViewById(R.id.bein3);
    bein3v.setColorFilter(farbeBeine);
    ImageView bein4v = view.findViewById(R.id.bein4);
    bein4v.setColorFilter(farbeBeine);

    ImageView bauchv = view.findViewById(R.id.bauch);
    bauchv.setColorFilter(checkColor(bauch));
    ImageView rueckenv = view.findViewById(R.id.ruecken);
    rueckenv.setColorFilter(checkColor(ruecken));

    int farbeSchultern = checkColor(schultern);
    ImageView schultern1v = view.findViewById(R.id.schultern);
    schultern1v.setColorFilter(farbeSchultern);
    ImageView schultern2v = view.findViewById(R.id.schultern2);
    schultern2v.setColorFilter(farbeSchultern);

    ImageView brustv = view.findViewById(R.id.brust);
    brustv.setColorFilter(checkColor(brust));

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  private int checkColor(int amount) {
    if (amount >= 4) {
      return Color.RED;
    } else if (amount == 3) {
      return Color.YELLOW;
    } else if (amount <= 2 && amount > 0) {
      return Color.GREEN;
    } else {
      return Color.WHITE;
    }
  }


  private Date[] getTodayAndLimit(int limit) {
    Date[] dates = new Date[2];

    final Calendar calendar = new GregorianCalendar();

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    dates[0] = calendar.getTime();


    calendar.add(Calendar.DAY_OF_YEAR, -limit);
    dates[1] = calendar.getTime();


    return dates;
  }
}
