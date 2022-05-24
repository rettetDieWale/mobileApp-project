package com.example.fithub.main.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

  List<String> names;
  private FragmentListLayoutBinding binding;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view =
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_layout, container, false);

    names = new ArrayList<>();

    RecyclerView recyclerView = view.findViewById(R.id.listLayout);
    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    recyclerView.setAdapter(new ListAdapter(names));

    return view;
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
