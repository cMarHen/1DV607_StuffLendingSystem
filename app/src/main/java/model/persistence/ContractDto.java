package model.persistence;

/**
 * Class ContractDto.
 */
public class ContractDto {
  private int startDay;
  private int endDay;
  private int totalContractFee;
  private String itemId;
  private String lenderId;
  private Boolean isValidated;

  /**
   * Constructor for ContractDto.
   */
  public ContractDto(
      int startDay,
      int endDay,
      int totalContractFee,
      String itemId,
      String lenderId,
      Boolean isValidated
  ) {
    this.lenderId = lenderId;
    this.endDay = endDay;
    this.itemId = itemId;
    this.startDay = startDay;
    this.totalContractFee = totalContractFee;
    this.isValidated = isValidated;
  }

  public int getEndDay() {
    return endDay;
  }

  public String getItemId() {
    return itemId;
  }

  public String getLenderId() {
    return lenderId;
  }

  public int getStartDay() {
    return startDay;
  }

  public int getTotalContractFee() {
    return totalContractFee;
  }

  public Boolean getIsValidated() {
    return isValidated;
  }
}
