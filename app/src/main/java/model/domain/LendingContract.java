package model.domain;

/**
 * Class to form a lending contract with all details.
 *
 */
public class LendingContract {
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
    // TODO: Supress findbug to reflekt member and item.
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

  /**
   * Get a copy of the Item-object representing the item being loaned.
   *
   * @return - A copy of the item being loaned.
   */
  public Item getItem() {
    Item itemCopy = new Item(
        this.item.getOwner(),
        this.item.getType(),
        this.item.getName(),
        this.item.getDescription(),
        this.item.getId(),
        this.item.getDayOfCreation(),
        this.item.getCostPerDay(),
        this.item.getIsReserved());

    return itemCopy;
  }

  /**
   * Get a copy of the Member-object representing the member loaning the item.
   *
   * @return - A copy of the member loaning the item.
   */
  public Member getLender() {
    Member lenderCopy = new Member(
        this.lender.getFirstName(),
        this.lender.getLastName(),
        this.lender.getEmail(),
        this.lender.getPhoneNumber(),
        this.lender.getId(),
        this.lender.getRegistredDay(),
        this.lender.getCredits());

    return lenderCopy;
  }

  public int getStartDay() {
    return startDay;
  }
}
