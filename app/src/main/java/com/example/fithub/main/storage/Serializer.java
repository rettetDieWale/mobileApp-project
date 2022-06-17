package com.example.fithub.main.storage;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/** Class for converting objects into json format. */
public class Serializer {

  /** gson converter object */
  private Gson gson;

  /**
   * Serialize objects into json and stores them into a file.
   *
   * @param context of the current activity
   * @param object to be serialized
   * @param filename filename as savefile object
   */
  public void serialize(final Context context, final Object object, final Savefile filename) {

    final String path = filename.toString();

    this.gson = new Gson();
    final String json = this.gson.toJson(object);

    final Storage storage = new Storage();
    storage.storeData(context, path, json);
  }

  /**
   * Deserialize objects back from json into type.
   *
   * @param context of current activity
   * @param type of class that json should be deserialized into
   * @param filename filename as savefile object
   * @return data object or null if data is empty
   */
  public Object deserialize(final Context context, final Type type, final Savefile filename) {

    final String path = filename.toString();

    final Storage storage = new Storage();
    final String data = storage.loadData(context, path);

    if (data.isEmpty()) {
      return null;
    }

    this.gson = new Gson();
    return this.gson.fromJson(data, type);
  }
}
