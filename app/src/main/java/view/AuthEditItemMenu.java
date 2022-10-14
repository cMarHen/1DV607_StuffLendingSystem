package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as authenticated user Menu.
 */
public class AuthEditItemMenu extends Menu {
  /**
   * Choices for the authenticated item menu.
   *
   */
  public static enum AuthEditItemMenuChoices {
    EditName,
    EditDescription,
    EditCost,
    Back
  }

  public AuthEditItemMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Edit Item ---------- -");
    System.out.println("------------------------");
    System.out.println("1. Edit name");
    System.out.println("2. Edit description");
    System.out.println("3. Edit cost per day");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= AuthEditItemMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.EditName;
    } else if (choice == 2) {
      return MenuEvent.EditDescription;
    } else if (choice == 3) {
      return MenuEvent.EditCost;
    } else {
      return MenuEvent.Back;
    }
  }
}
