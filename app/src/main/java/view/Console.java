package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
  Scanner scan;

  public static enum MainEvent {
    MemberMenu,
    ItemMenu,
    Quit
  }

  public static enum MemberEvent {
    AddMember,
    ListMember,
    DeleteMember,
    Back
  }

  public Console(Scanner scan) {
    this.scan = scan;
  }

  public MainEvent getMainMenuChoice() {
    // TODO: Maybe a separate class.
    System.out.println("------------------------");
    System.out.println("- Stuff Lending System -");
    System.out.println("------------------------");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Forward day");
    System.out.println("0. Quit ");
    System.out.println("------------------------");

    int choice = scan.nextInt();

    if (choice == 1) {
      return MainEvent.MemberMenu;
    } else if (choice == 2) {
      return MainEvent.ItemMenu;
    } else {
      return MainEvent.Quit;
    }
  }

  public MemberEvent getMemberMenuChoice() {
    // TODO: Maybe a separate class.
    System.out.println("------------------------");
    System.out.println("- Member Menu -------- -");
    System.out.println("------------------------");
    System.out.println("1. Add Member");
    System.out.println("2. List Members");
    System.out.println("3. Delete Member");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = scan.nextInt();

    if (choice == 1) {
      return MemberEvent.AddMember;
    } else if (choice == 2) {
      return MemberEvent.ListMember;
    } else if (choice == 3) {
      return MemberEvent.DeleteMember;
    } else {
      return MemberEvent.Back;
    }
  }

  public void printMemberList(ArrayList<model.domain.Member> members) {
    System.out.println(" --- ");
    for (model.domain.Member member : members) {
      System.out.println(member.getFirstName() + " " + member.getLastName());
      System.out.println("Created day " + member.getRegistredDay() + ". ");
      System.out.println("Id: " + member.getId());
      System.out.println(" --- ");
    }
  }

  public String promptForAnswer(String question) {
    System.out.println(question);
    String choice = scan.next();

    return choice;
  }
}
