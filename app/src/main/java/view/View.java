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

  public View(Scanner scan, Printer printer) {
    this.scan = scan;
    this.printer = printer;
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

  public abstract void notifyCurrentDay(int currentDay);
}
