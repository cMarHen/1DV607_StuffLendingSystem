package view;

import java.util.ArrayList;
import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Abstraction of a view.
 *
 */
public abstract class View {
  protected Scanner scan;
  public Printer printer;

  public View(Scanner scan) {
    this.scan = scan;
    this.printer = new ConsolePrinter();
  }
  /**
   * Prints the main menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public abstract MenuEvent getMainMenuChoice();

  /**
   * Prints the member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public abstract MenuEvent getMemberMenuChoice();

  /**
   * Prints the edit-member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public abstract MenuEvent getEditMemberMenuChoice();

  /**
   * Prints the item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public abstract MenuEvent getItemMenuChoice();

  /**
   * Prints the edit-item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public abstract MenuEvent getEditItemMenuChoice();

  /**
   * Prints menu based on the available item types reads the user choice of type.
   *
   * @return - The users item-type choice.
   */
  public abstract model.domain.Item.ItemType getItemTypeMenuChoice();

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public abstract void printMemberList(
      Iterable<? extends model.domain.Member> members,
      Iterable<model.domain.Item.Mutable> items
  );

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public abstract void printVerboseMember(model.domain.Member.Mutable member);
  
  /**
   * Prints the list of all items with name, type, availibility-status and id.
   * If no items in the list a message is printed to signal this to the user.
   *
   */
  public abstract void printItemList(Iterable<? extends model.domain.Item> items);

  /**
   * Prints a single members details, including name, email, phone number and credits.
   *
   * @param member - The member to read all the data from.
   */
  public abstract void printDetailedMember(model.domain.Member member);

  /**
   * Prints a single items name, type, description, cost per day, contracts and availibility-status.
   *
   * @param item - The item to read all data from.
   */
  public abstract void printDetailedItem(
      model.domain.Item item, ArrayList<model.domain.LendingContract> activeContracts,
      ArrayList<model.domain.LendingContract> expiredContracts
  );

  /**
   * Request user-inputs needed to register a new member.
   *
   * @return - Readonly object with member-information.
   */
  public abstract model.domain.Member promptForNewMember();

  /**
   * Request user-inputs needed to add a new item.
   *
   * @return - Readonly object with item-information.
   */
  public abstract model.domain.Item promptForNewItem();

  /**
   * Request user-input string for password.
   *
   * @return - Entered string.
   */
  public abstract String promptForPassword();

  /**
   * Request user-input string for item id.
   *
   * @return - Entered string.
   */
  public abstract String promptForItemId();

  /**
   * Request user-input string for member id.
   *
   * @return - Entered string.
   */
  public abstract String promptForMemberId();

  /**
   * Request user-input string for first name.
   *
   * @return - Entered string.
   */
  public abstract String promptForFirstName();

  /**
   * Request user-input string for last name.
   *
   * @return - Entered string.
   */
  public abstract String promptForLastName();

  /**
   * Request user-input string for email.
   *
   * @return - Entered string.
   */
  public abstract String promptForEmail();

  /**
   * Request user-input string for phone.
   *
   * @return - Entered string.
   */
  public abstract String promptForPhone();

  /**
   * Request user-input string for item name.
   *
   * @return - Entered string.
   */
  public abstract String promptForItemName();

  /**
   * Request user-input string for description.
   *
   * @return - Entered string.
   */
  public abstract String promptForItemDescription();

  /**
   * Request user-input string for cost per day.
   *
   * @return - Entered amount.
   */
  public abstract int promptForCostPerDay();

  /**
   * Request user-input string for loan start day.
   *
   * @return - Entered amount.
   */
  public abstract int promptForLoanStartDay();

  /**
   * Request user-input string for amount of days to loan.
   *
   * @return - Entered amount.
   */
  public abstract int promptForDaysToLoan();

  /**
   * Request user-input string for amount of days proceed.
   *
   * @return - Entered amount.
   */
  public abstract int promptForDaysToProceed();

  public abstract void notifyCurrentDay(int currentDay);

  protected int promptForInt(String message) {
    int input = -1;
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextInt();
        scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        scan.nextLine();
        validInput = false;
      }
    } while (!validInput);

    return input;
  }

  protected String promptForString(String message) {
    String input = "";
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        validInput = false;
      }
    } while (!validInput);

    return input;
  }
}
