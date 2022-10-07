package view;

/**
 * Uses the console to print messages.
 *
 */
public class ConsolePrinter implements Printer {
  public void printInvalidPassword() {
    System.out.println("Password must be 8 characters long.");
  }

  @Override
  public void printCurrentDay(int currentDay) {
    System.out.println("The current day is: " + currentDay);
  }

  @Override
  public void printCreateMemberSuccess() {
    System.out.println("Member successfully created!");
  }

  @Override
  public void printCreateMemberError() {
    System.out.println("Member could not be created!");
  }

  @Override
  public void printCreateItemSuccess() {
    System.out.println("Item successfully created!");
  }

  @Override
  public void printCreateItemError() {
    System.out.println("Item could not be created!");
  }

  @Override
  public void printDeleteMemberSuccess() {
    System.out.println("Successfully deleted member!");
  }

  @Override
  public void printDeleteMemberError() {
    System.out.println("Could not delete this member!");
  }

  @Override
  public void printDeleteItemSuccess() {
    System.out.println("Successfully deleted item!");
  }

  @Override
  public void printDeleteItemError() {
    System.out.println("Could not delete this item!");
  }

  @Override
  public void printCreateContractSuccess() {
    System.out.println("Lending contract was successfully set up!");
  }

  @Override
  public void printCreateContractError() {
    System.out.println("Could not set up lending contract!");
  }

  @Override
  public void printFindMemberError() {
    System.out.println("Could not find a member with this ID!");
  }

  @Override
  public void printFindItemError() {
    System.out.println("Could not find an item with this ID!");
  }

  @Override
  public void printDuplicateEmail() {
    System.out.println("This email is already in use!");
  }

  @Override
  public void printDuplicatePhone() {
    System.out.println("This phonenumber is already in use!");
  }
}
