package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Abstraction of a view.
 *
 */
public abstract class View {
  protected Scanner scan;
  public Printer printer;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
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
   * @param query - Flag if query to present different.
   * @return - Entered string.
   */
  public abstract String promptForItemName(boolean query);

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

  /**
   * Request user to confirm delete.
   *
   * @return - The users decision.
   */
  public abstract boolean promptDeleteMemberConfirmation();

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

  protected boolean promptForConfirmation(String question) {
    String confirmation;
    do {
      System.out.print(question + " (Y/N) ");
      confirmation = scan.next();
      scan.nextLine();
    } while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"));

    if (confirmation.equalsIgnoreCase("y")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Prints menu based on the available item types reads the user choice of type.
   *
   * @return - The users item-type choice.
   */
  public model.domain.Item.ItemType getItemTypeMenuChoice() {
    for (int i = 1; i <= model.domain.Item.ItemType.values().length; i++) {
      System.out.println(i + ". " + model.domain.Item.ItemType.values()[i - 1]);
    }

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= model.domain.Item.ItemType.values().length || choice < 0);

    return model.domain.Item.ItemType.values()[choice - 1];
  }

}
