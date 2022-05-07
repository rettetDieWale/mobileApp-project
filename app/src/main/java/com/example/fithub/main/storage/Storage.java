package com.example.fithub.main.storage;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Storage {

  /**
   * Store data into a text file. Starts path at: data/data/app/com.example.fithub/files/
   *
   * @param context of the current Activity
   * @param fileName for destination file
   */
  public void storeData(@NonNull final Context context, final String fileName, final String data) {

    FileOutputStream fileOutputStream = null;

    try {
      fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);

      final byte[] ByteArray = data.getBytes();

      fileOutputStream.write(ByteArray);
      fileOutputStream.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String loadData(Context context, String fileName) {

    FileInputStream fileInputStream = null;
    final StringBuilder stringBuilder = new StringBuilder();
    try {
      fileInputStream = context.openFileInput(fileName);
      final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }

      inputStreamReader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return stringBuilder.toString();
  }
}
