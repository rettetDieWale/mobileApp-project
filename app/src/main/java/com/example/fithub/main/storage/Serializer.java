package com.example.fithub.main.storage;

import android.content.Context;

import com.google.gson.Gson;

/** Class for converting objects into json format. */
public class Serializer {

  /** gson converter object */
  Gson gson;

  /**
   * Serialize objects into json and stores them into a file.
   *
   * @param context of the current activity
   * @param object to be serialized
   */
  public void serialize(Context context, Object object) {

    this.gson = new Gson();
    String json = this.gson.toJson(object);

    Storage storage = new Storage();
    storage.storeData(context, "Demo.txt", json);
  }

  /**
   * Deserialize objects back from json into type.
   *
   * @param context of current activity
   * @param type of class that json should be deserialized into.
   * @return
   */
  public Object deserialize(Context context, Class<?> type) {

    Storage storage = new Storage();
    String data = storage.loadData(context, "Demo.txt");
    this.gson = new Gson();
    return this.gson.fromJson(data, type);
  }
}
