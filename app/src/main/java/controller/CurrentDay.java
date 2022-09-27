package controller;

/**
 * Painted class for the current day.
 *
 */
public class CurrentDay {
  private int currentDay;

  public CurrentDay() {
    this.currentDay = 0;
  }

  public void incrementDay() {
    this.currentDay++;
  }

  public int getCurrentDay() {
    return currentDay;
  }
}
