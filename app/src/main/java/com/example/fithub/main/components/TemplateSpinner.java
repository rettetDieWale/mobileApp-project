package com.example.fithub.main.components;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.fithub.R;

/** Class for spinner selecting templates. */
public class TemplateSpinner {
  private Spinner spinner;

  /**
   * Instantiates a new template spinner.
   *
   * @param view the spinner is attached to
   * @param context of the activity the spinner is used
   * @param spinnerId R.id of the component the adapter is set
   */
  public TemplateSpinner(View view, Context context, int spinnerId) {
    this.spinner = (Spinner) view.findViewById(spinnerId);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            context, R.array.template_array, android.R.layout.simple_spinner_item);

    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Apply the adapter to the spinner
    this.spinner.setAdapter(adapter);
  }
}
