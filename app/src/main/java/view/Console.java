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
    DetailedMember,
    EditMember,
    DeleteMember,
    Back
  }

  public static enum MemberEditEvent {
    EditFirstName,
    EditLastName,
    EditEmail,
    EditPhone,
    Back
  }

  public static enum ItemEvent {
    AddItem,
    ListItems,
    DetailedItem,
    EditItem,
    LendItem,
    DeleteItem,
    Back
  }

  public Console(Scanner scan) {
    this.scan = scan;
    /* this.memberConsole = new MemberConsole(scan) */
    /* this.itemConsole = new ItemConsole(scan) */
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
    System.out.println("3. Show detailed member");
    System.out.println("4. Edit a member");
    System.out.println("5. Delete Member");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = scan.nextInt();

    if (choice == 1) {
      return MemberEvent.AddMember;
    } else if (choice == 2) {
      return MemberEvent.ListMember;
    } else if (choice == 3) {
      return MemberEvent.DetailedMember;
    } else if (choice == 4) {
      return MemberEvent.EditMember;
    } else if (choice == 5) {
      return MemberEvent.DeleteMember;
    } else {
      return MemberEvent.Back;
    }
  }

  public MemberEditEvent getEditMemberMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Edit Member -------- -");
    System.out.println("------------------------");
    System.out.println("1. Edit first name");
    System.out.println("2. Edit last name");
    System.out.println("3. Edit email");
    System.out.println("4. Edit phone number");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = scan.nextInt();

    if (choice == 1) {
      return MemberEditEvent.EditFirstName;
    } else if (choice == 2) {
      return MemberEditEvent.EditLastName;
    } else if (choice == 3) {
      return MemberEditEvent.EditEmail;
    } else if (choice == 4) {
      return MemberEditEvent.EditPhone;
    } else {
      return MemberEditEvent.Back;
    }
  } 

  public ItemEvent getItemMenuChoice() {
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

    int choice = scan.nextInt();

    if (choice == 1) {
      return ItemEvent.AddItem;
    } else if (choice == 2) {
      return ItemEvent.ListItems;
    } else if (choice == 3) {
      return ItemEvent.DetailedItem;
    } else if (choice == 4) {
      return ItemEvent.EditItem;
    } else if (choice == 5) {
      return ItemEvent.LendItem;
    } else if (choice == 6) {
      return ItemEvent.DeleteItem;
    } else {
      return ItemEvent.Back;
    }
  }

  public model.domain.Item.ItemType getItemTypeMenuChoice() {
    for (int i = 1; i < model.domain.Item.ItemType.values().length; i++) {
      System.out.println(i + ". " + model.domain.Item.ItemType.values()[i - 1]);
    }

    int choice = scan.nextInt();

    return model.domain.Item.ItemType.values()[choice - 1];
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
  
  public void printItemList(ArrayList<model.domain.Item> items) {
    
    if (items.size() == 0) {
      System.out.println("No items to show.");
    }
    
    for (model.domain.Item item : items) {
      System.out.println(" --- ");
      System.out.println("Name: " + item.getName());
      System.out.println("Type: " + item.getType());
      System.out.println("Avialiable: " + (item.getIsReserved() ? "no" : "yes"));
      System.out.println("Id: " + item.getId());
    }
  }

  public void printDetailedMember(model.domain.Member member) {
    System.out.println("Name: " + member.getFirstName() + " " + member.getLastName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("PhoneNumber: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
  }

  public void printDetailedItem(model.domain.Item item) {
    System.out.println(" --- ");
    System.out.println("Name: " + item.getName());
    System.out.println("Type: " + item.getType());
    System.out.println("Description: " + item.getDescription());
    System.out.println("Cost per day: " + item.getCostPerDay());
    // TODO: implementera contract view
    System.out.println("Contracts: " + " IMPLEMENTERA!!!!!! ");
    System.out.println("Avialiable: " + (item.getIsReserved() ? "no" : "yes"));
  }

  public String promptForAnswer(String question) {
    System.out.println(question);
    String choice = scan.next();

    return choice;
  }

  public int promptForIntAnswer(String question) {
    System.out.println(question);
    int choice = scan.nextInt();

    return choice;
  }

  public void printActionResponse(String message) {
    System.out.println(message);
  }
}
