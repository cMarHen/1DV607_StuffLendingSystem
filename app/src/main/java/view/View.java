package view;

import java.util.ArrayList;

import controller.MainController.PromptEvent;
import view.MainView.MenuEvent;

/**
 * Interface for implementing menus.
 *
 */
public interface View {
  /**
   * Prints the main menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MenuEvent getMainMenuChoice();

  /**
   * Prints the member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MenuEvent getMemberMenuChoice();

  /**
   * Prints the edit-member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MenuEvent getEditMemberMenuChoice();

  /**
   * Prints the item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MenuEvent getItemMenuChoice();

  /**
   * Prints the edit-item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MenuEvent getEditItemMenuChoice();

  /**
   * Prints menu based on the available item types reads the user choice of type.
   *
   * @return - The users item-type choice.
   */
  public model.domain.Item.ItemType getItemTypeMenuChoice();

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public void printMemberList(
      Iterable<? extends model.domain.Member> members,
      Iterable<model.domain.Item.Mutable> items
  );

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public void printVerboseMember(model.domain.Member.Mutable member);
  
  /**
   * Prints the list of all items with name, type, availibility-status and id.
   * If no items in the list a message is printed to signal this to the user.
   *
   */
  public void printItemList(Iterable<? extends model.domain.Item> items);

  /**
   * Prints a single members details, including name, email, phone number and credits.
   *
   * @param member - The member to read all the data from.
   */
  public void printDetailedMember(model.domain.Member member);

  /**
   * Prints a single items name, type, description, cost per day, contracts and availibility-status.
   *
   * @param item - The item to read all data from.
   */
  public void printDetailedItem(
      model.domain.Item item, ArrayList<model.domain.LendingContract> activeContracts,
      ArrayList<model.domain.LendingContract> expiredContracts
  );

  /**
   * Prompt user for a PromptEvent.
   *
   * @param event - Type of Prompt to do to the user.
   * @return - The user input as String.
   */
  public String promptInformation(PromptEvent event);

  /**
   * Prompt user for a PromptEvent.
   *
   * @param event - Type of Prompt to do to the user.
   * @return - The user input as Integer.
   */
  public int promptInformationInt(PromptEvent event);

  /**
   * Prints a message to the user based on action-event.
   * (E.g, member not found, unable to set up lending contract etc.)
   *
   * @param actionResponse - The response from controller of type enum ActionEvent.
   */
  public void actionResponder(controller.MainController.ActionEvent actionResponse);

  /**
   * Request user-inputs needed to register a new member.
   *
   * @return - Readonly object with member-information.
   */
  public model.domain.Member promptForNewMember();

  /**
   * Request user-inputs needed to add a new item.
   *
   * @return - Readonly object with item-information.
   */
  public model.domain.Item promptForNewItem();

  public void notifyCurrentDay(int currentDay);
}
