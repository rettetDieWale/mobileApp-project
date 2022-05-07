package com.example.fithub.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentFirstBinding;
import com.example.fithub.main.calendar.CalendarActivity;
import com.example.fithub.main.storage.Storage;

public class FirstFragment extends Fragment {

  private FragmentFirstBinding binding;

  private TextView textview;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // binding = FragmentFirstBinding.inflate(inflater, container, false);

    final View view = inflater.inflate(R.layout.fragment_first, container, false);

    final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    progressBar.setMax(100);
    progressBar.setProgress(50);
    textview = view.findViewById(R.id.stored_data);

    createOnClickListeners(view);

    return view;
  }

  public void createOnClickListeners(View view) {

    final Button calendarButton = (Button) view.findViewById(R.id.button_home);
    calendarButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    final Button storeButton = view.findViewById(R.id.button_save);
    storeButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Storage storage = new Storage();
            storage.storeData(getActivity(), "Demo.txt");
            String data = storage.loadData(getActivity(), "Demo.txt");

            textview.setText(data);
          }
        });
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
