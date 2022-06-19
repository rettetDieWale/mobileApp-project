package com.example.fithub.main.components;

/**
 * Item class for spinner and list items, needed because otherwise spinner/list can only use simple
 * data types.
 */
public class Item {
  private int id;
  private String name;

  /**
   * Instantiates a new Item.
   *
   * @param id the id
   * @param name the name
   */
  public Item(final int id, final String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  // to display object as a string in spinner
  @Override
  public String toString() {
    return name;
  }
}
