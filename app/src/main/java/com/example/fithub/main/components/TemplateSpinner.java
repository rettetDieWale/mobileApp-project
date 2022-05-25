package com.example.fithub.main.components;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/** Interface class for spinners selecting our templates. */
public class TemplateSpinner {
  private Spinner spinner;

  /**
   * Instantiates a new template spinner.
   *
   * @param view the spinner is attached to
   * @param context of the activity the spinner is used
   * @param spinnerId R.id of the component the adapter is set
   * @param items
   */
  public TemplateSpinner(View view, Context context, int spinnerId, ArrayList<Item> items) {

    this.spinner = (Spinner) view.findViewById(spinnerId);

    ArrayAdapter<Item> adapter =
        new ArrayAdapter<Item>(context, android.R.layout.simple_spinner_dropdown_item, items);

    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    this.spinner.setAdapter(adapter);
  }

  /**
   * Gets spinner associated with the current template spinner.
   *
   * @return spinner object
   */
  public Spinner getSpinner() {
    return this.spinner;
  }
}
