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

  private List<String> list;
  private int[] elementIdList;
  private Fragment fragment;

  /**
   * @param list of strings (training plan names)
   * @param elementIdList id of each training plan in the list
   * @param fragment current fragment
   */
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

    position = holder.getAdapterPosition();

    holder.planName.setText(list.get(position));
    int finalPosition = position;

    int entriesCount =
        DatabaseManager.appDatabase.planEntryDao().getCountByPlanId(elementIdList[finalPosition]);
    holder.entryNumberView.setText(Integer.toString(entriesCount) + " Übungen");

    holder.planName.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            view.setBackgroundColor(fragment.getResources().getColor(R.color.primary_light));

            Bundle args = new Bundle();
            args.putInt("trainingPlanId", elementIdList[finalPosition]);
            args.putInt("actionId", 0);

            NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_trainingPlanOverviewFragment_to_trainingPlanFragment, args);
          }
        });
  }

  @Override
  public int getItemCount() {
    return list.size();
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
