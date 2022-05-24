package com.example.fithub.main.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fithub.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

  private List<String> list;

  public ListAdapter(List<String> list) {
    this.list = list;
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
