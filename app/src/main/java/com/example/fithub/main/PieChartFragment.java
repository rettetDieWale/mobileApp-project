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
import com.example.fithub.databinding.FragmentPiechartBinding;
import com.example.fithub.main.prototypes.MuscleGroupChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieChartFragment extends Fragment {

  private FragmentPiechartBinding binding;
  private PieChart chart;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_piechart, container, false);

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    chart = (PieChart) view.findViewById(R.id.chart1);

    MuscleGroupChart chartData = new MuscleGroupChart();

    // raw test data for testing purposes only
    ArrayMap<String, String> testData = new ArrayMap<>();
    testData.put("Schultern", "18.5f");
    testData.put("Brust", "26.7f");
    testData.put("Rücken", "10.1f");
    testData.put("Arme", "24.0f");
    testData.put("Beine", "30.8f");
    testData.put("Bauch", "11.4f");

    // todo: sort entries by size

    List<PieEntry> entries = new ArrayList<>();

    for (Map.Entry<String, String> entry : testData.entrySet()) {
      entries.add(new PieEntry(Float.parseFloat(entry.getValue()), entry.getKey()));
    }

    PieDataSet set = new PieDataSet(entries, "Muskelgruppen trainiert");
    chart.setEntryLabelColor(Color.BLACK);

    int[] Colors = {
      Color.rgb(79, 118, 247),
      Color.rgb(146, 79, 247),
      Color.rgb(124, 247, 79),
      Color.rgb(247, 244, 79),
      Color.rgb(247, 127, 79),
      Color.rgb(247, 79, 79)
    };

    set.setColors(Colors);
    final PieData data = new PieData(set);
    chart.setData(data);
    chart.invalidate(); // refresh
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
