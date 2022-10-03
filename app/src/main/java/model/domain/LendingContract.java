package model.domain;

/**
 * Class to form a lending contract with all details.
 *
 */
public class LendingContract implements Comparable<LendingContract> {
  private int startDay;
  private int endDay;
  private int totalContractFee;
  private Item item;
  private Member lender;

  /**
   * Instanciate a lending contract with the data presented as parameters.
   *
   * @param lender - A copy of the whom to pay credits for the total lending-fee.
   * @param endDay - The day after the endDay the item is available for loan again.
   * @param item - The item to copy wich holds information about the owner to recieve credits for the loan.
   * @param startDay - From this day the item is reserved until the day after endDay.
   */
  public LendingContract(Member lender, int endDay, Item item, int startDay) {
    this.lender = lender;
    this.endDay = endDay;
    this.item = item;
    this.startDay = startDay;
    this.totalContractFee = (endDay - startDay) * item.getCostPerDay();
  }

  public int getEndDay() {
    return this.endDay;
  }

  public int getTotalContractFee() {
    return this.totalContractFee;
  }

  public Item getItem() {
    return this.item;
  }

  public Member getLender() {
    return this.lender;
  }

  public int getStartDay() {
    return startDay;
  }

  /**
   * Compare contracts by startDay.
   *
   * @return - Int to signal sorting-order.
   */
  public int compareTo(LendingContract contract) {
    int thisStartDay = this.getStartDay();
    int compareToStartDay = contract.getStartDay();

    if (thisStartDay > compareToStartDay) {
      return -1;
    } else if (thisStartDay < compareToStartDay) {
      return 1;
    } else {
      return 0;
    }
  }

}
