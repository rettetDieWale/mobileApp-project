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

    setHumanImageMuscleGroupImages();

    return view;
  }

  /** Setup the msucle groups for the human image and colorize them. */
  private void setHumanImageMuscleGroupImages() {
    final Date[] dates = getTodayAndLimit(7);

    final int amountLegsTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(0, dates[0], dates[1]);
    final int amountChestTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(1, dates[0], dates[1]);
    final int amountArmsTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(2, dates[0], dates[1]);
    final int amountShouldersTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(3, dates[0], dates[1]);
    final int amountCoreTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(4, dates[0], dates[1]);
    final int amountBackTrained =
        DatabaseManager.appDatabase
            .trainingDayMuscleGroupCrossRefDao()
            .countPastDays(5, dates[0], dates[1]);

    final int colorArms = checkColor(amountArmsTrained);
    final ImageView arm1v = view.findViewById(R.id.arm1);
    final ImageView arm2v = view.findViewById(R.id.arm2);
    final ImageView arm3v = view.findViewById(R.id.arm3);
    final ImageView arm4v = view.findViewById(R.id.arm4);
    arm1v.setColorFilter(colorArms);
    arm2v.setColorFilter(colorArms);
    arm3v.setColorFilter(colorArms);
    arm4v.setColorFilter(colorArms);

    final int colorLegs = checkColor(amountLegsTrained);
    final ImageView leg1v = view.findViewById(R.id.bein1);
    leg1v.setColorFilter(colorLegs);
    final ImageView leg2v = view.findViewById(R.id.bein2);
    leg2v.setColorFilter(colorLegs);
    final ImageView leg3v = view.findViewById(R.id.bein3);
    leg3v.setColorFilter(colorLegs);
    final ImageView leg4v = view.findViewById(R.id.bein4);
    leg4v.setColorFilter(colorLegs);

    final ImageView corev = view.findViewById(R.id.bauch);
    corev.setColorFilter(checkColor(amountCoreTrained));
    final ImageView backv = view.findViewById(R.id.ruecken);
    backv.setColorFilter(checkColor(amountBackTrained));

    final int colorShoulders = checkColor(amountShouldersTrained);
    final ImageView shoulders1v = view.findViewById(R.id.schultern);
    shoulders1v.setColorFilter(colorShoulders);
    final ImageView shoulders2v = view.findViewById(R.id.schultern2);
    shoulders2v.setColorFilter(colorShoulders);

    final ImageView chestv = view.findViewById(R.id.brust);
    chestv.setColorFilter(checkColor(amountChestTrained));
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  /**
   * Set the color pattern for each amount trained.
   *
   * @param amount muscle groups have been trained
   * @return color code
   */
  private int checkColor(final int amount) {
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

  /**
   * Get date interval from today based on limit.
   *
   * @param limit that is subtracted from today
   * @return interval of days
   */
  private Date[] getTodayAndLimit(final int limit) {
    final Date[] dates = new Date[2];

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
