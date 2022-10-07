package view;

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
}
