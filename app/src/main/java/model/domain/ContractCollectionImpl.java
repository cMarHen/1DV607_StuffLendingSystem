package model.domain;

import java.util.ArrayList;

/**
 * Wrapper-class to manage a collection of LendingContracts.
 *
 */
public class ContractCollectionImpl implements ContractCollection {
  private ArrayList<LendingContract> contracts; // Should only contain active or future contracts
  private ArrayList<LendingContract> history;

  /**
   * Class is instanciated by setting the contracts to empty arraylist.
   *
   */
  public ContractCollectionImpl() {
    contracts = new ArrayList<>();
    history = new ArrayList<>();
  }
  
  /**
   * Adds contract to the contracts-list if contract is valid.
   * A valid contract is if lender have sufficient credits and item is available.
   *
   * @param contract - The contract to validate and add to collection if valid.
   * @return - Flag if successfully added contract to contracts-list.
   */
  @Override
  public boolean addContract(LendingContract contract) {
    if (contract != null && isValidContract(contract)) {
      contracts.add(contract);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get a list with copies of all expired contracts.
   *
   * @return - List with copies of all expired contracts.
   */
  @Override
  public ArrayList<LendingContract> getExpiredContracts() {
    ArrayList<LendingContract> historyContracts = new ArrayList<>();

    for (LendingContract contract : history) {
      LendingContract contractCopy = new LendingContract(
          contract.getLender(),
          contract.getEndDay(),
          contract.getItem(),
          contract.getStartDay());

      historyContracts.add(contractCopy);
    }

    return historyContracts;
  }

  /**
   * Adds expired contracts to history and removes the contract from the active/future contracts list.
   *
   * @param currentDay - The day-number to compare with to find expired contracts.
   * @return - A list with copies of the expired contracts.
   */
  @Override
  public ArrayList<LendingContract> cleanExpiredContracts(int currentDay) {
    ArrayList<LendingContract> expiredContracts = new ArrayList<>();
    // TODO: Something went wrong here
    for (LendingContract contract : contracts) {
      if (contract.getEndDay() < currentDay) {
        LendingContract contractCopy = new LendingContract(
            contract.getLender(),
            contract.getEndDay(),
            contract.getItem(),
            contract.getStartDay());
        contracts.remove(contract);
        history.add(contractCopy);
        expiredContracts.add(contractCopy);
      }
    }

    return expiredContracts;
  }

  /**
   * Search wheter an item has any active contracts.
   * 
   * @param id - Id for the item to look for.
   * @return- Flag if item has any active contract.
   */
  @Override
  public boolean itemHasActiveContract(String id) {
    for (LendingContract contract : contracts) {
      String itemId = contract.getItem().getId();
      if (id.equals(itemId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds the contracts which just has reached its startDay and should have matching items reserved.
   *
   * @param currentDay - The day-number to compare with to find expired contracts.
   * @return - A list with copies of contracts that just has reached its startDay.
   */
  @Override
  public ArrayList<LendingContract> getActivatedContracts(int currentDay) {
    ArrayList<LendingContract> activatedContracts = new ArrayList<>();

    for (LendingContract contract : contracts) {
      if (contract.getStartDay() <= currentDay) {
        LendingContract contractCopy = new LendingContract(
            contract.getLender(),
            contract.getEndDay(),
            contract.getItem(),
            contract.getStartDay());
        activatedContracts.add(contractCopy);
      }
    }

    return activatedContracts;
  }

  /**
   * Finds all active contracts for a specific item.
   *
   * @param item - Item to query for match by id in contract.
   */
  public ArrayList<LendingContract> getContractsByItem(Item item) {
    ArrayList<LendingContract> contractsByItem = new ArrayList<>();

    for (LendingContract contract : contracts) {
      if (item.getId().equals(contract.getItem().getId())) {
        contractsByItem.add(contract);
      }
    }

    return contractsByItem;
  }

  /**
   * Finds all expired contracts for a specific item.
   *
   * @param item - Item to query for match by id in contract.
   */
  public ArrayList<LendingContract> getExpiredContractsByItem(Item item) {
    ArrayList<LendingContract> expiredContractsByItem = new ArrayList<>();

    for (LendingContract contract : history) {
      if (item.getId().equals(contract.getItem().getId())) {
        expiredContractsByItem.add(contract);
      }
    }

    return expiredContractsByItem;
  }

  /**
   * Search whether a owner has an item in not yet expired contracts.
   *
   * @param memberId - Id for the owner to look for.
   * @return - Flag if any owner item is in an active contract.
   */
  @Override
  public boolean ownerIsInActiveContract(String memberId) {
    for (LendingContract contract : contracts) {
      String ownerId = contract.getItem().getOwner().getId();
      if (memberId.equals(ownerId)) {
        return true;
      }
    }

    return false;
  }

  /**
   * A valid contract is if lender have sufficient credits and item is available.
   *
   * @param contract - The contract to validate.
   * @return - Flag if a valid contract.
   */
  private boolean isValidContract(LendingContract contract) {
    int totalLendingCost = contract.getTotalContractFee();
    int lenderCredits = contract.getLender().getCredits();

    // TODO: Is owner setting up contracts for its own items? No need to check for credits.

    if (!contract.getItem().getIsReserved() && lenderCredits > totalLendingCost) {
      return true;
    }

    return false;
  }
}
