package view;

import java.util.Scanner;
import view.MainView.MenuEvent;

public class AuthMemberMenu extends Menu {
  /**
   * Choices for the authenticated member menu.
   *
   */
  public static enum AuthMemberMenuChoices {
    ListMember,
    ListMemberVerbose,
    DetailedMember,
    EditMember,
    DeleteMember,
    Back
  }

  public AuthMemberMenu(Scanner scan) {
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
    System.out.println("4. Edit a member");
    System.out.println("5. Delete Member");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= AuthMemberMenuChoices.values().length || choice < 0);

    if (choice == 1) {
      return MenuEvent.ListMember;
    } else if (choice == 2) {
      return MenuEvent.ListMemberVerbose;
    } else if (choice == 3) {
      return MenuEvent.DetailedMember;
    } else if (choice == 4) {
      return MenuEvent.EditMember;
    } else if (choice == 5) {
      return MenuEvent.DeleteMember;
    } else {
      return MenuEvent.Back;
    }
  }
  
}
