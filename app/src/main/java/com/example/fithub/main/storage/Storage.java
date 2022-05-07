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

  public void storeData(@NonNull Context context, String fileName) {
    String Data = "Test Data has been saved into Demo.txt";

    FileOutputStream fileOutputStream = null;

    try {
      fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);

      byte[] ByteArray = Data.getBytes();

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
    StringBuilder stringBuilder = new StringBuilder();
    try {
      fileInputStream = context.openFileInput(fileName);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

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
