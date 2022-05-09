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
  private Serializer serializer;
  private Storage storage;

  @Before
  public void preparations() {
    this.appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    this.serializer = new Serializer();
    this.storage = new Storage();
  }

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Assert.assertEquals("com.example.fithub", appContext.getPackageName());
  }

  @Test
  public void checkSerializedContent() {

    final String json_result = "{\"age\":20,\"name\":\"Max Mustermann\"}";

    final Person person = new Person("Max Mustermann", 20);
    this.serializer.serialize(this.appContext, person, Savefile.TEST_FILE1);

    final String json_stored =
        this.storage.loadData(this.appContext, Savefile.TEST_FILE1.toString());

    Assert.assertTrue(json_stored.equals(json_result));
  }

  @Test
  public void testDeserialization() {
    final Person person = new Person("Max Mustermann", 20);

    final Person person2 =
        (Person) serializer.deserialize(this.appContext, Person.class, Savefile.TEST_FILE1);

    Assert.assertTrue(person.name.equals(person2.name));
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
