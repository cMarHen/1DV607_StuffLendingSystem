package model.domain;

/**
 * Painted class for the current day.
 *
 */
public class CurrentDay {
  private int currentDay;
  private StuffLendingSystem sls;

  public CurrentDay(StuffLendingSystem sls) {
    this.sls = sls;
    this.currentDay = 0;
  }

  public void incrementDay() {
    this.currentDay++;
    sls.notifyIncrementedDay(currentDay);
  }

  public int getCurrentDay() {
    return currentDay;
  }
}
