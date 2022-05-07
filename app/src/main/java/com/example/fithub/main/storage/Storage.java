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

  public String storeData(@NonNull Context context) {
    String File_Name = "Demo.txt"; // gives file name
    String Data = "Test Data has been saved into Demo.txt"; // define data

    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;
    StringBuilder stringBuilder = new StringBuilder();
    try {

      // write
      fileOutputStream = context.openFileOutput(File_Name, Context.MODE_PRIVATE);

      byte[] ByteArray = Data.getBytes(); // Converts into bytes stream

      fileOutputStream.write(ByteArray); // writing to file
      fileOutputStream.close(); // File closed

      // read
      fileInputStream = context.openFileInput(File_Name);
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
