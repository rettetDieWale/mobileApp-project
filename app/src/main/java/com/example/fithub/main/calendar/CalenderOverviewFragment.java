package com.example.fithub.main.calendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentCalenderOverviewBinding;
import com.example.fithub.main.MainActivity;
import com.example.fithub.main.trainingplan.TrainingPlanActivity;

public class CalenderOverviewFragment extends Fragment {

    private FragmentCalenderOverviewBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCalenderOverviewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCalenderReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), MainActivity.class);
                 startActivity(intent);

            }
        });

        binding.buttonTrainingplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrainingPlanActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}