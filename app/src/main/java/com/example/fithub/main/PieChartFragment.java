package com.example.fithub.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.main.prototypes.MuscleGroupChart;
import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PieChartFragment extends Fragment {

  private PieChart chart;
  private MuscleGroupChart muscleGroupChart;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_piechart, container, false);
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

  /** Fills the pie chart with initial data. Useful when files are corrupted or not existing. */
  private void resetChart() {
    // raw test data for testing purposes only
    final ArrayMap<String, String> MuscleData = new ArrayMap<>();
    MuscleData.put("Schultern", "18.5f");
    MuscleData.put("Brust", "26.7f");
    MuscleData.put("Rücken", "10.1f");
    MuscleData.put("Arme", "24.0f");
    MuscleData.put("Beine", "30.8f");
    MuscleData.put("Bauch", "11.4f");

    this.muscleGroupChart = new MuscleGroupChart();
    this.muscleGroupChart.addDataAll(MuscleData);
  }

  /**
   * Adds entries into the chart and defines their color.
   *
   * @param pieEntries that are rendered into the chart.
   */
  private void renderChart(final List<PieEntry> pieEntries) {
    final PieDataSet set = new PieDataSet(pieEntries, "Muskelgruppen trainiert");
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
}
