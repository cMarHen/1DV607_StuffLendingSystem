package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as authenticated user Menu.
 */
public class AuthItemMenu extends Menu {
  /**
   * Choices for the authenticated item menu.
   *
   */
  public static enum AuthItemMenuChoices {
    AddItem,
    ListItems,
    SearchItems,
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
    System.out.println("3. Search items");
    System.out.println("4. Show detailed item");
    System.out.println("5. Edit item");
    System.out.println("6. Lend item");
    System.out.println("7. Delete item");
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
      return MenuEvent.SearchItems;
    } else if (choice == 4) {
      return MenuEvent.DetailedItem;
    } else if (choice == 5) {
      return MenuEvent.EditItem;
    } else if (choice == 6) {
      return MenuEvent.LendItem;
    } else if (choice == 7) {
      return MenuEvent.DeleteItem;
    } else {
      return MenuEvent.Back;
    }
  }
}
