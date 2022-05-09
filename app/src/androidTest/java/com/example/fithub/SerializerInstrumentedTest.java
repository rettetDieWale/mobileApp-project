package com.example.fithub;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.fithub.main.storage.Savefile;
import com.example.fithub.main.storage.Serializer;
import com.example.fithub.main.storage.Storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SerializerInstrumentedTest {
  private Context appContext;

  @Before
  public void preparations() {
    this.appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
  }

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Assert.assertEquals("com.example.fithub", appContext.getPackageName());
  }

  @Test
  public void checkSerializedContent() {

    String json_result = "{\"age\":20,\"name\":\"Max Mustermann\"}";

    Serializer serializer = new Serializer();
    Person person = new Person("Max Mustermann", 20);
    serializer.serialize(this.appContext, person, Savefile.TEST_FILE1);

    Storage storage = new Storage();
    String json_stored = storage.loadData(this.appContext, Savefile.TEST_FILE1.toString());

    Assert.assertEquals(true, json_stored.equals(json_result));
  }
}

/** Test class */
class Person {
  String name;
  int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
