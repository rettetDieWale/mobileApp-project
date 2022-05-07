package com.example.fithub.main.storage;

import android.content.Context;

import com.google.gson.Gson;

/** Class for converting objects into json format. */
public class Serializer {

  public String serialize(Context context) {
    progressBarExp pbe = new progressBarExp();
    pbe.max = 200;
    pbe.progress = 60;

    Gson gson = new Gson();
    String json = gson.toJson(pbe);

    Storage storage = new Storage();
    storage.storeData(context, "Demo.txt", json);
    String data = storage.loadData(context, "Demo.txt");

    return data;
  }
}

/** example class for progress bar will be deleted later */
class progressBarExp {
  int max;
  int progress;
}
