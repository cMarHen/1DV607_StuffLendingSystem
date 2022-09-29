package model.domain;

import java.util.ArrayList;

/**
 * Wrapper-class to manage a collection of LendingContracts.
 *
 */
public class ContractCollection {
  private ArrayList<LendingContract> contracts; // Should only contain active or future contracts
  // TODO: Add history-contracts, to minimize looping power.

  /**
   * Class is instanciated by setting the contracts to empty arraylist.
   *
   */
  public ContractCollection() {
    contracts = new ArrayList<>();
  }
  
  /**
   * Adds contract to the contracts-list if contract is valid.
   * A valid contract is if lender have sufficient credits and item is available.
   *
   * @param contract - The contract to validate and add to collection if valid.
   * @return - Flag if successfully added contract to contracts-list.
   */
  public boolean addContract(LendingContract contract) {
    if (contract != null && isValidContract(contract)) {
      contracts.add(contract);
      return true;
    } else {
      return false;
    }
  }

  public void compareCurrentDayWithContracts(int currentDay) {
    for (LendingContract contract : contracts) {
      if ((contract.getStartDay() <= currentDay) && (contract.getEndDay() >= currentDay)) {
        contract.setItemReservation(true);
      } else {
        contract.setItemReservation(false);
      }
    }
  }

  /**
   * A valid contract is if lender have sufficient credits and item is available.
   *
   * @param contract - The contract to validate.
   * @return - Flag if a valid contract.
   */
  private boolean isValidContract(LendingContract contract) {
    int totalLendingCost = getContractFee(contract);
    int lenderCredits = contract.getLender().getCredits();

    // TODO: Is owner setting up contracts for its own items? No need to check for credits.

    if (!contract.getItem().getIsReserved() && lenderCredits > totalLendingCost) {
      return true;
    }

    return false;
  }

  /**
   * Calculates the total contract-fee based on days to be loaned and the Items cost per day.
   *
   * @param contract - Contract to read from to calculate the total fee.
   * @return - The calculated total contract-fee.
   */
  public int getContractFee(LendingContract contract) {
    // TODO: Move this to the constructor in LendingContract to save a history of prices.
    return (contract.getEndDay() - contract.getStartDay()) * contract.getItem().getCostPerDay();
  }
}
