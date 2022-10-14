package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as not-authenticated user Menu.
 */
public class ItemMenu extends Menu {
  /**
   * Choices for the item menu.
   *
   */
  public static enum ItemMenuChoices {
    ListItems,
    DetailedItem,
    Back
  }

  public ItemMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Item Menu ---------- -");
    System.out.println("------------------------");
    System.out.println("1. List items");
    System.out.println("2. Show detailed item");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= ItemMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.ListItems;
    } else if (choice == 2) {
      return MenuEvent.DetailedItem;
    } else {
      return MenuEvent.Back;
    }
  }
}
