package com.example.fithub.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.MuscleGroupChart;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.TrainingDay;
import com.example.fithub.main.prototypes.data.TrainingDayMuscleGroupCrossRef;
import com.example.fithub.main.prototypes.data.TrainingPlan;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PieChartFragment extends Fragment {

    private PieChart chart;
    private MuscleGroupChart muscleGroupChart;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_piechart, container, false);



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
        ImageView arm1v = v.findViewById(R.id.arm1);
        ImageView arm2v = v.findViewById(R.id.arm2);
        ImageView arm3v = v.findViewById(R.id.arm3);
        ImageView arm4v = v.findViewById(R.id.arm4);
        arm1v.setColorFilter(farbeArme);
        arm2v.setColorFilter(farbeArme);
        arm3v.setColorFilter(farbeArme);
        arm4v.setColorFilter(farbeArme);

        int farbeBeine = checkColor(beine);
        ImageView bein1v = v.findViewById(R.id.bein1);
        bein1v.setColorFilter(farbeBeine);
        ImageView bein2v = v.findViewById(R.id.bein2);
        bein2v.setColorFilter(farbeBeine);

        ImageView bauchv = v.findViewById(R.id.bauch);
        bauchv.setColorFilter(checkColor(bauch));
        ImageView rueckenv = v.findViewById(R.id.rücken);
        rueckenv.setColorFilter(checkColor(ruecken));

        int farbeSchultern = checkColor(schultern);
        ImageView schultern1v = v.findViewById(R.id.Schultern);
        schultern1v.setColorFilter(farbeSchultern);
        ImageView schultern2v = v.findViewById(R.id.schultern2);
        schultern2v.setColorFilter(farbeSchultern);

        ImageView brustv = v.findViewById(R.id.Brust);
        brustv.setColorFilter(checkColor(brust));


        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initChart();
        this.chart = (PieChart) view.findViewById(R.id.chart1);

        final List<PieEntry> pieEntries = new ArrayList<>();
        for (Map.Entry<String, String> entry : this.muscleGroupChart.getAllData().entrySet()) {
            pieEntries.add(new PieEntry(Float.parseFloat(entry.getValue()), entry.getKey()));
        }

        sortChart(pieEntries);

        renderChart(pieEntries);
    }

    /**
     * Parses the number of training days by muscle groups and add them to the array map data.
     *
     * @return muscleData as ArrayMap(muscle group:string, count:string)
     */
    private ArrayMap<String, String> parseMuscleGroupData() {

        final List<MuscleGroup> muscleGroupList = DatabaseManager.appDatabase.muscleGroupDao().getAll();
        final ArrayMap<String, String> muscleData = new ArrayMap<>();

        for (int i = 0; i < muscleGroupList.size(); i++) {
            final String muscleGroupName = muscleGroupList.get(i).getMuscleGroupName();
            final int muscleGroupId = muscleGroupList.get(i).getMuscleGroupId();
            final int count =
                    DatabaseManager.appDatabase
                            .trainingDayMuscleGroupCrossRefDao()
                            .countByMuscleGroupId(muscleGroupId);
            muscleData.put(muscleGroupName, String.valueOf(count));
        }

        return muscleData;
    }

    /**
     * Fills the pie chart with initial data. Useful when files are corrupted or not existing.
     */
    private void resetChart() {

        // raw test data for testing purposes only
        final ArrayMap<String, String> muscleData = parseMuscleGroupData();

        this.muscleGroupChart = new MuscleGroupChart();
        this.muscleGroupChart.addDataAll(muscleData);
    }

    /**
     * Adds entries into the chart and defines their color.
     *
     * @param pieEntries that are rendered into the chart.
     */
    private void renderChart(final List<PieEntry> pieEntries) {
        final PieDataSet set = new PieDataSet(pieEntries, "Gruppen");
        this.chart.setEntryLabelColor(Color.BLACK);

        final int[] Colors = {
                Color.rgb(79, 118, 247),
                Color.rgb(146, 79, 247),
                Color.rgb(124, 247, 79),
                Color.rgb(247, 244, 79),
                Color.rgb(247, 127, 79),
                Color.rgb(247, 79, 79)
        };

        set.setColors(Colors);
        final PieData data = new PieData(set);
        this.chart.setData(data);
        this.chart.invalidate(); // refresh data - no idea why its called invalidate in lib
    }

    /**
     * Sort the List with PieEntries in order to the biggest entry being first.
     *
     * @param pieEntries to be sorted
     */
    private void sortChart(final List<PieEntry> pieEntries) {
        Collections.sort(
                pieEntries,
                (Object a1, Object a2) -> {
                    final PieEntry pe1 = (PieEntry) a1;
                    final PieEntry pe2 = (PieEntry) a2;
                    // sorting so biggest value starts on right side of the donut chart
                    return Float.compare(pe2.getValue(), pe1.getValue());
                });
    }

    /**
     * Initializes the chart data based on savefile or if file is not existing/corrupted with example
     * data.
     */
    private void initChart() {
        final Serializer serializer = new Serializer();
        this.muscleGroupChart =
                (MuscleGroupChart)
                        serializer.deserialize(
                                getActivity(), MuscleGroupChart.class, Savefile.MUSCLE_GROUP_CHART_SAVEFILE);

        if (this.muscleGroupChart == null) {
            resetChart();
        }
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
