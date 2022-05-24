package com.example.fithub.main.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

  private List<String> list;
  private int[] elementIdList;
  private Fragment fragment;

  public ListAdapter(List<String> list, int[] elementIdList, Fragment fragment) {
    this.list = list;
    this.elementIdList = elementIdList;
    this.fragment = fragment;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MyViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.textView.setText(list.get(position));
    holder.textView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            Bundle args = new Bundle();
            args.putInt("exerciseDataId", elementIdList[position]);

            NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_training_plan_overview_to_training_plan, args);
          }
        });
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.textView);
    }
  }
}
