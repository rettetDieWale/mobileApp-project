package com.example.fithub.main.trainingplan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingPlanBinding;


public class TrainingPlanFragment extends Fragment {

    private FragmentTrainingPlanBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_plan, container, false);
    }
}