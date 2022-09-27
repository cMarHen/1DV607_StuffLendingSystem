package model.domain;

import java.util.ArrayList;

/**
 * Wrapper-class to manage a collection of LendingContracts.
 *
 */
public class ContractCollection {
  private ArrayList<LendingContract> contracts;

  /**
   * Class is instanciated by setting the contracts to empty arraylist.
   *
   */
  public ContractCollection() {
    contracts = new ArrayList<>();
  }
  
  public boolean addContract(LendingContract contract) {
    if (contract != null && isValidContract(contract)) {
      contracts.add(contract);
      return true;
    } else {
      return false;
    }
  }

  private boolean isValidContract(LendingContract contract) {
    int totalLendingCost = getContractFee(contract);
    int lenderCredits = contract.getLender().getCredits();

    if (!contract.getItem().getIsReserved() && lenderCredits > totalLendingCost) {
      return true;
    }

    return false;
  }

  public int getContractFee(LendingContract contract) {
    return (contract.getEndDay() - contract.getStartDay()) * contract.getItem().getCostPerDay();
  }
}
