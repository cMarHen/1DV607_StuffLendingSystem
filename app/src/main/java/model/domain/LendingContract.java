package model.domain;

public class LendingContract {
  private int startDay;
  private int endDay;
  private Item item;
  private Member lender;

  public LendingContract (Member lender, int endDay, Item item, int startDay) {
    this.lender = lender;
    this.endDay = endDay;
    this.item = item;
    this.startDay = startDay;
    // TODO: Calculate contract-price.
  }

  public int getEndDay() {
    return endDay;
  }

  public Item getItem() {
    return item;
  }

  public Member getLender() {
    return lender;
  }

  public int getStartDay() {
    return startDay;
  }
}
