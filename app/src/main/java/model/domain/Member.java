package model.domain;

import java.util.ArrayList;

import model.domain.Item.ItemType;

public class Member {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String id;
  private int credits;
  private int registredDay;
  private ArrayList<Item> ownedItems;

  public Member(String firstName, String lastName, String email, String phoneNumber, String id, int registredDay) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = 0;
    this.id = id;
    this.registredDay = registredDay;

    this.ownedItems = new ArrayList<>();
  }

  public Item addItem(ItemType type, String name, String description, String id, int dayOfCreation, int costPerDay) {
    Item newItem = new Item(type, name, description, id, dayOfCreation, costPerDay);

    ownedItems.add(newItem);

    return newItem;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getCredits() {
    return credits;
  }

  public void addCredits(int credits) {
    this.credits += credits;
  }

  public void removeCredits(int credits) {
    credits -= credits;
  }

  public String getId() {
    return id;
  }

  public int getRegistredDay() {
    return registredDay;
  }
}
