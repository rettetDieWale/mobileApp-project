package com.example.fithub.main.storage;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Storage {

  public void storeData(@NonNull Context context) {
    String File_Name = "Demo.txt"; // gives file name
    String Data = "Hello!!"; // define data

    FileOutputStream fileobj = null;
    try {
      fileobj = context.openFileOutput(File_Name, Context.MODE_APPEND);

      byte[] ByteArray = Data.getBytes(); // Converts into bytes stream

      fileobj.write(ByteArray); // writing to file
      fileobj.close(); // File closed

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
