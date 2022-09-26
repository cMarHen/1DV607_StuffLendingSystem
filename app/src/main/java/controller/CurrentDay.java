package controller;

public class CurrentDay {
  private int currentDay;

  public CurrentDay () {
    this.currentDay = 0;
  }

  public void incrementDay () {
    this.currentDay++;
  }

  public int getCurrentDay() {
    return currentDay;
  }
}
