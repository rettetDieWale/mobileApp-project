package com.example.fithub.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPiechartBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PieChart chart = (PieChart) view.findViewById(R.id.chart1);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "Schultern"));
        entries.add(new PieEntry(26.7f, "Brust"));
        entries.add(new PieEntry(24.0f, "Arme"));
        entries.add(new PieEntry(30.8f, "Beine"));

        PieDataSet set = new PieDataSet(entries, "Election Results");

        set.setColors(Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE);
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
