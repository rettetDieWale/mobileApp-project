package com.example.fithub.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentFirstBinding.inflate(inflater, container, false);

    View view = inflater.inflate(R.layout.fragment_first, container, false);
    TextView textView = (TextView) view.findViewById(R.id.text_view_storage);

    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    binding.buttonHome.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            /**
             * NavHostFragment.findNavController(FirstFragment.this)
             * .navigate(R.id.action_FirstFragment_to_calenderFragment);
             */
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
          }
        });

    binding.buttonSave.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Storage storage = new Storage();
            storage.storeData(getActivity());
          }
        });

    // setup progress bar
    final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    progressBar.setMax(100);
    progressBar.setProgress(50);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
