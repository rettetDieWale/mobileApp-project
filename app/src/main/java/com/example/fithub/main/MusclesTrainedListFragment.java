package com.example.fithub.main;

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

public class MusclesTrainedListFragment extends Fragment {

    String[] names = {"hello", "bye", "main", "joe", "give", "true", "false", "know", "list", "hello", "bye" };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_layout, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListAdapter(names));

        return view;
    }
}
