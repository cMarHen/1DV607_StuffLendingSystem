package view;

import java.util.ArrayList;

/**
 * Sets the contract to be followed by printers.
 *
 */
public interface Printer {
  public void printCurrentDay(int currentDay);

  public void printInvalidPassword();

  public void printCreateMemberSuccess();

  public void printCreateMemberError();

  public void printCreateItemSuccess();

  public void printCreateItemError();

  /* public void printEditMemberSuccess();
  public void printEditMemberError();
  public void printEditItemSuccess();
  public void printEditItemError(); */

  public void printDeleteMemberSuccess();

  public void printDeleteMemberError();

  public void printDeleteItemSuccess();

  public void printDeleteItemError();

  public void printCreateContractSuccess();

  public void printCreateContractError();

  public void printFindMemberError();

  public void printFindItemError();

  public void printDuplicateEmail();

  public void printDuplicatePhone();

  public void printMemberId(String id);

  public void printLoginSuccess();

  public void printAuthorizationError();

  public void printMemberList(
      Iterable<? extends model.domain.Member> members,
      Iterable<model.domain.Item.Mutable> items
  );

  public void printVerboseMember(model.domain.Member.Mutable member);

  public void printItemList(Iterable<? extends model.domain.Item> items);

  public void printDetailedMember(model.domain.Member member);
  
  public void printDetailedItem(
      model.domain.Item item, ArrayList<model.domain.LendingContract> activeContracts,
      ArrayList<model.domain.LendingContract> expiredContracts
  );
}
