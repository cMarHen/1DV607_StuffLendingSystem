package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

public class AuthItemMenu extends Menu {
  /**
   * Choices for the authenticated item menu.
   *
   */
  public static enum AuthItemMenuChoices {
    AddItem,
    ListItems,
    DetailedItem,
    EditItem,
    LendItem,
    DeleteItem,
    Back
  }

  public AuthItemMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Item Menu ---------- -");
    System.out.println("------------------------");
    System.out.println("1. Add new item");
    System.out.println("2. List items");
    System.out.println("3. Show detailed item");
    System.out.println("4. Edit item");
    System.out.println("5. Lend item");
    System.out.println("6. Delete item");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= AuthItemMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.AddItem;
    } else if (choice == 2) {
      return MenuEvent.ListItems;
    } else if (choice == 3) {
      return MenuEvent.DetailedItem;
    } else if (choice == 4) {
      return MenuEvent.EditItem;
    } else if (choice == 5) {
      return MenuEvent.LendItem;
    } else if (choice == 6) {
      return MenuEvent.DeleteItem;
    } else {
      return MenuEvent.Back;
    }
  }
}
