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
    } else {
      return MenuEvent.Quit;
    }
  }
  
}
