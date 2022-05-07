package com.example.fithub.main.storage;

import android.content.Context;

import com.google.gson.Gson;

/** Class for converting objects into json format. */
public class Serializer {

  Gson gson;

  public void serialize(Context context, Object object) {

    this.gson = new Gson();
    String json = this.gson.toJson(object);

    Storage storage = new Storage();
    storage.storeData(context, "Demo.txt", json);
  }

  public Object deserialize(Context context, Class<?> type) {

    Storage storage = new Storage();
    String data = storage.loadData(context, "Demo.txt");
    this.gson = new Gson();
    return this.gson.fromJson(data, type);
  }
}
