package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Class as not-authenticated user Menu.
 */
public class MemberMenu extends Menu {
  /**
   * Choices for the member menu.
   *
   */
  public static enum MemberMenuChoices {
    ListMember,
    ListMemberVerbose,
    DetailedMember,
    Back
  }

  public MemberMenu(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Member Menu -------- -");
    System.out.println("------------------------");
    System.out.println("1. List Members simple");
    System.out.println("2. List Members verbose");
    System.out.println("3. Show detailed member");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MemberMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.ListMember;
    } else if (choice == 2) {
      return MenuEvent.ListMemberVerbose;
    } else if (choice == 3) {
      return MenuEvent.DetailedMember;
    } else {
      return MenuEvent.Back;
    }
  }
  
}
