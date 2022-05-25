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
   * @param templateNames string list with names of the templates
   */
  public TemplateSpinner(
      View view, Context context, int spinnerId, ArrayList<String> templateNames) {
    this.spinner = (Spinner) view.findViewById(spinnerId);

    ArrayAdapter<String> adapter =
        new ArrayAdapter<String>(
            context, android.R.layout.simple_spinner_dropdown_item, templateNames);

    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Apply the adapter to the spinner
    this.spinner.setAdapter(adapter);
  }
}
