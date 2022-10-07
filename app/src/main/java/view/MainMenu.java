package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

public class MainMenu extends Menu {
  /**
   * Choices for the main menu.
   *
   */
  public static enum MainMenuChoices {
    MemberMenu,
    ItemMenu,
    Login,
    Register,
    Quit
  }

  public MainMenu (Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Stuff Lending System -");
    System.out.println("------------------------");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Log in");
    System.out.println("4. Register");
    System.out.println("0. Quit ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MainMenuChoices.values().length || choice < 0);


    if (choice == 1) {
      return MenuEvent.MemberMenu;
    } else if (choice == 2) {
      return MenuEvent.ItemMenu;
    } else if (choice == 3) {
      return MenuEvent.Login;
    } else if (choice == 4) {
      return MenuEvent.Register;
    } else {
      return MenuEvent.Quit;
    }
  }
  
}
