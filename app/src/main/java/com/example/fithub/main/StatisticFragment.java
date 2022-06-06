package com.example.fithub.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fithub.R;

public class StatisticFragment extends Fragment {
  private View view;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {}
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_statistic, container, false);

    final FragmentManager fragmentManager = getParentFragmentManager();
    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    final PieChartFragment pieChartFragment = new PieChartFragment();
    final HumanChartFragment humanChartFragment = new HumanChartFragment();

    fragmentTransaction.add(R.id.fragmentContainerViewPieChart, pieChartFragment);
    fragmentTransaction.add(R.id.fragmentContainerViewHumanFragment, humanChartFragment);
    fragmentTransaction.commit();

    return view;
  }
}
