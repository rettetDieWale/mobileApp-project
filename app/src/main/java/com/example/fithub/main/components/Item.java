package com.example.fithub.main.components;

/**
 * Item class for spinner and list items, needed because otherwise spinner/list can only use simple
 * data types.
 */
public class Item {
  private int id;
  private String name;

  public Item(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // to display object as a string in spinner
  @Override
  public String toString() {
    return name;
  }
}
