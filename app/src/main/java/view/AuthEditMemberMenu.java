package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as authenticated user Menu.
 */
public class AuthEditMemberMenu extends Menu {
  /**
   * Choices for the authenticated item menu.
   *
   */
  public static enum AuthEditMemberMenuChoices {
    EditFirstName,
    EditLastName,
    EditEmail,
    EditPhone,
    Back
  }

  public AuthEditMemberMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Edit Member -------- -");
    System.out.println("------------------------");
    System.out.println("1. Edit first name");
    System.out.println("2. Edit last name");
    System.out.println("3. Edit email");
    System.out.println("4. Edit phone number");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= AuthEditMemberMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.EditFirstName;
    } else if (choice == 2) {
      return MenuEvent.EditLastName;
    } else if (choice == 3) {
      return MenuEvent.EditEmail;
    } else if (choice == 4) {
      return MenuEvent.EditPhone;
    } else {
      return MenuEvent.Back;
    }
  }
}
