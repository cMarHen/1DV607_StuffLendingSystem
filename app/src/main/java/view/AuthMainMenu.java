package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as authenticated user Menu.
 */
public class AuthMainMenu extends Menu {
  /**
   * Choices for the authenticated main menu.
   *
   */
  public static enum AuthMainMenuChoices {
    MemberMenu,
    ItemMenu,
    ForwardDay,
    Logout,
    Quit
  }

  public AuthMainMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Stuff Lending System -");
    System.out.println("------------------------");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Forward day");
    System.out.println("4. Log out");
    System.out.println("0. Quit ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= AuthMainMenuChoices.values().length || choice < 0);


    if (choice == 1) {
      return MenuEvent.MemberMenu;
    } else if (choice == 2) {
      return MenuEvent.ItemMenu;
    } else if (choice == 3) {
      return MenuEvent.ForwardDay;
    } else if (choice == 4) {
      return MenuEvent.Logout;
    } else {
      return MenuEvent.Quit;
    }
  }
  
}
