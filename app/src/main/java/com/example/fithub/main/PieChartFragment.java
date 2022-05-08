package com.example.fithub.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentPiechartBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartFragment extends Fragment {

  private FragmentPiechartBinding binding;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_piechart, container, false);

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    PieChart chart = (PieChart) view.findViewById(R.id.chart1);

    // todo: sort entrys by size

    List<PieEntry> entries = new ArrayList<>();
    entries.add(new PieEntry(18.5f, "Schultern"));
    entries.add(new PieEntry(26.7f, "Brust"));
    entries.add(new PieEntry(10.1f, "Rücken"));
    entries.add(new PieEntry(24.0f, "Arme"));
    entries.add(new PieEntry(30.8f, "Beine"));
    entries.add(new PieEntry(11.4f, "Bauch"));

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
    PieData data = new PieData(set);
    chart.setData(data);
    chart.invalidate(); // refresh
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
