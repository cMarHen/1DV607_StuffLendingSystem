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
   * Instanciate a lending contract based on another contract that holds the data.
   *
   * @param contract - A contract holding all information needed to create a new contract.
   */
  public LendingContract(LendingContract contract) {
    this.lender = contract.getLender();
    this.endDay = contract.getEndDay();
    this.item = contract.getItem();
    this.startDay = contract.getStartDay();
    this.totalContractFee = contract.getTotalContractFee();
  }

  /**
   * Instanciate a lending contract with the data presented as parameters.
   *
   * @param lender - A readonly member whom to pay credits for the total lending-fee.
   * @param endDay - The day after the endDay the item is available for loan again.
   * @param item - The readonly item wich holds information about the owner to recieve credits for the loan.
   * @param startDay - From this day the item is reserved until the day after endDay.
   */
  public LendingContract(Member lender, int endDay, Item item, int startDay) {
    this.lender = lender;
    this.endDay = endDay;
    this.item = item;
    this.startDay = startDay;
    this.totalContractFee = ((endDay + 1)- startDay) * item.getCostPerDay();
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
    Integer thisStartDay = this.getStartDay();
    Integer compareToStartDay = contract.getStartDay();
    
    // TODO: Test this method
    return thisStartDay.compareTo(compareToStartDay);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof LendingContract)) {
      return false;
    }
    return compareTo((LendingContract) obj) == 0;
  }

  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 42;
  }
}
