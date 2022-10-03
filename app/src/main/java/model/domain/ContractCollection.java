package model.domain;

import java.util.ArrayList;

/**
 * Interface setting the contract for an ContractCollection.
 *
 */
public interface ContractCollection {
  public boolean addContract(LendingContract contract);

  public ArrayList<LendingContract> getExpiredContracts();

  public ArrayList<LendingContract> getContractsByItem(Item item);

  public ArrayList<LendingContract> getExpiredContractsByItem(Item item);

  public ArrayList<LendingContract> cleanExpiredContracts(int currentDay);

  public boolean itemHasActiveContract(String id);
  
  public ArrayList<LendingContract> getActivatedContracts(int currentDay);
  
  public boolean ownerIsInActiveContract(String memberId);
}
