package model.domain;

/**
 * Class to form a lending contract with all details.
 *
 */
public class LendingContract {
  private int startDay;
  private int endDay;
  private Item item;
  private Member lender;

  /**
   * Instanciate a lending contract with the data presented as parameters.
   *
   * @param lender - Used as a reference to a member whom to pay credits for the total lending-fee.
   * @param endDay - The day after the endDay the item is available for loan again.
   * @param item - The item holds information about the owner to recieve credits for the loan.
   * @param startDay - From this day the item is reserved until the day after endDay.
   */
  public LendingContract(Member lender, int endDay, Item item, int startDay) {
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
