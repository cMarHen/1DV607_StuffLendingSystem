package model.domain;

import java.util.LinkedList;

public class Item {
  private LinkedList<LendingContract> history;
  private boolean isReserved;
  private ItemType type;
  private String name;
  private String description;
  private String id;
  private int dayOfCreation;
  private int costPerDay;

  public static enum ItemType {
    Tool,
    Vehicle,
    Game,
    Toy,
    Sport,
    Other
  }

  public Item(ItemType type, String name, String description, String id, int dayOfCreation, int costPerDay) {
    this.type = type;
    this.name = name;
    this.description = description;
    this.id = id;
    this.dayOfCreation = dayOfCreation;
    this.costPerDay = costPerDay;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getIsReserved() {
    return isReserved;
  }

  public void setReserved(boolean isReserved) {
    this.isReserved = isReserved;
  }

  public String getId() {
    return id;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public int getDayOfCreation() {
    return dayOfCreation;
  }

  public ItemType getType() {
    return type;
  }
}
