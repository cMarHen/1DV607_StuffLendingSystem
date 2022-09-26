package model.domain;

import java.util.ArrayList;

public class ContractCollection {
  private ArrayList<LendingContract> contracts;

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
