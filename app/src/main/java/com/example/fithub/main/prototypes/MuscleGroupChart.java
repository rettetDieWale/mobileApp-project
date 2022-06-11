package com.example.fithub.main.prototypes;

import android.util.ArrayMap;

import java.util.Map;

/** Muscle group data prototype for serialization */
public class MuscleGroupChart {
  private ArrayMap<String, String> muscleGroupData;

  public MuscleGroupChart() {
    muscleGroupData = new ArrayMap<>();
  }

  /**
   * Add date for muscle group to the structure.
   *
   * @param key to find data
   * @param data that should be saved
   */
  public void addData(final String key, final float data) {
    muscleGroupData.put(key, String.valueOf(data));
  }

  /**
   * Add a complete list to replace current data.
   *
   * @param data that replaces list.
   */
  public void addDataAll(final ArrayMap<String, String> data) {
    muscleGroupData = data;
  }

  /**
   * Get data from the muscle group structure.
   *
   * @param key data is saved under
   * @return data for given key
   */
  public float getData(final String key) {
    return Float.parseFloat(muscleGroupData.get(key));
  }

  /**
   * Get data of all entrys inside the structure.
   *
   * @return data as Map
   */
  public Map<String, String> getAllData() {
    return muscleGroupData;
  }
}
