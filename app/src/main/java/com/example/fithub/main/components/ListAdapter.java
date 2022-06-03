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
import com.example.fithub.main.prototypes.data.DatabaseManager;

import java.util.List;

/** List adapter class for containing training plans. */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

  private List<Item> items;
  private Fragment fragment;

  /**
   * @param items for the list
   * @param fragment current fragment
   */
  public ListAdapter(final List<Item> items, final Fragment fragment) {
    this.items = items;
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

    final int finalPosition = holder.getAdapterPosition();

    holder.planName.setText(items.get(position).getName());

    final int entriesCount =
        DatabaseManager.appDatabase
            .planEntryDao()
            .getCountByPlanId(items.get(finalPosition).getId());
    holder.entryNumberView.setText(Integer.toString(entriesCount) + " Übungen");

    holder.planName.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            view.setBackgroundColor(fragment.getResources().getColor(R.color.primary_light));

            final Bundle args = new Bundle();
            args.putInt("trainingPlanId", items.get(finalPosition).getId());
            args.putInt("actionId", 0);

            NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_trainingPlanOverviewFragment_to_trainingPlanFragment, args);
          }
        });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView planName;
    TextView entryNumberView;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      planName = itemView.findViewById(R.id.plan_name);
      entryNumberView = itemView.findViewById(R.id.entry_number);
    }
  }
}
