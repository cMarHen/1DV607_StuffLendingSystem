package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MainController.ActionEvent;

/**
 * Main class and the Facade for the view.
 * 
 */
public class Console {
  Scanner scan;

  /**
   * Events emmited from the class that constructs the main-menu choices.
   *
   */
  public static enum MainEvent {
    MemberMenu,
    ItemMenu,
    ForwardDay,
    Quit
  }

  /**
   * Events emmited from the class that constructs the member-menu choices.
   *
   */
  public static enum MemberEvent {
    AddMember,
    ListMember,
    DetailedMember,
    EditMember,
    DeleteMember,
    Back
  }

  /**
   * Events emmited from the class that constructs the edit member-menu choices.
   *
   */
  public static enum MemberEditEvent {
    EditFirstName,
    EditLastName,
    EditEmail,
    EditPhone,
    Back
  }

  /**
   * Events emmited from the class that constructs the item-menu choices.
   *
   */
  public static enum ItemEvent {
    AddItem,
    ListItems,
    DetailedItem,
    EditItem,
    LendItem,
    DeleteItem,
    Back
  }

  /**
   * Instaciate the class and the scanner used for inputs and outputs in the UI.
   *
   */
  public Console() {
    this.scan = new Scanner(System.in, "UTF8");
  }

  /**
   * Prints the main menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MainEvent getMainMenuChoice() {
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
    } else if (choice == 3) {
      return MainEvent.ForwardDay;
    } else {
      return MainEvent.Quit;
    }
  }

  /**
   * Prints the member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public MemberEvent getMemberMenuChoice() {
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

  /**
   * Prints the edit-member menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
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

  /**
   * Prints the item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
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

  /**
   * Prints menu based on the available item types reads the user choice of type.
   *
   * @return - The users item-type choice.
   */
  public model.domain.Item.ItemType getItemTypeMenuChoice() {
    for (int i = 1; i < model.domain.Item.ItemType.values().length; i++) {
      System.out.println(i + ". " + model.domain.Item.ItemType.values()[i - 1]);
    }

    int choice = scan.nextInt();

    return model.domain.Item.ItemType.values()[choice - 1];
  }

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public void printMemberList(ArrayList<model.domain.Member> members) {
    System.out.println(" --- ");

    for (model.domain.Member member : members) {
      System.out.println(member.getFirstName() + " " + member.getLastName());
      System.out.println("Created day " + member.getRegistredDay() + ". ");
      System.out.println("Id: " + member.getId());
      System.out.println(" --- ");
    }
  }
  
  /**
   * Prints the list of all items with name, type, availibility-status and id.
   * If no items in the list a message is printed to signal this to the user.
   *
   */
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

  /**
   * Prints a single members details, including name, email, phone number and credits.
   *
   * @param member - The member to read all the data from.
   */
  public void printDetailedMember(model.domain.Member member) {
    System.out.println("Name: " + member.getFirstName() + " " + member.getLastName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("PhoneNumber: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
  }

  /**
   * Prints a single items name, type, description, cost per day, contracts and availibility-status.
   *
   * @param item - The item to read all data from.
   */
  public void printDetailedItem(model.domain.Item item) {
    // TODO: Needs lending-contracts for matching item.
    System.out.println(" --- ");
    System.out.println("Name: " + item.getName());
    System.out.println("Type: " + item.getType());
    System.out.println("Description: " + item.getDescription());
    System.out.println("Cost per day: " + item.getCostPerDay());
    System.out.println("Avialiable: " + (item.getIsReserved() ? "no" : "yes"));
    System.out.println("Contracts: " + " IMPLEMENTERA!!!!!! ");
  }

  /**
   * Prompt for user response in string-format.
   *
   * @param question - The text presented to the user describing what to respond to.
   * @return - The users response.
   */
  public String promptForAnswer(String question) {
    // TODO: Refactor to seperate methods for each question?
    System.out.println(question);
    String choice = scan.next();

    return choice;
  }

  /**
   * Prompt for user response in int-format.
   *
   * @param question - The text presented to the user describing what to respond to.
   * @return - The users response.
   */
  public int promptForIntAnswer(String question) {
    // TODO: Refactor to seperate methods for each question?
    System.out.println(question);
    int choice = scan.nextInt();

    return choice;
  }

  /**
   * Prints a message to the user based on action-event.
   * (E.g, member not found, unable to set up lending contract etc.)
   *
   * @param actionResponse - The response from controller of type enum ActionEvent.
   */
  public void actionResponder(controller.MainController.ActionEvent actionResponse) {
    // TODO: Refactor to ActionResponder and ActionEvents!

    if (actionResponse == ActionEvent.ERR_CREATE_MEMBER) {
      System.out.println("Member could not be created!");
    } else if (actionResponse == ActionEvent.SUCCESS_CREATE_MEMBER) {
      System.out.println("Member successfully created!");
    }  else if (actionResponse == ActionEvent.ERR_CREATE_ITEM) {
      System.out.println("Item could not be created!");
    } else if (actionResponse == ActionEvent.SUCCESS_CREATE_ITEM) {
      System.out.println("Item successfully created!");
    } else if (actionResponse == ActionEvent.ERR_EDIT_MEMBER) {
      System.out.println("");
    } else if (actionResponse == ActionEvent.SUCCESS_EDIT_MEMBER) {
      System.out.println("");
    } else if (actionResponse == ActionEvent.ERR_DELETE) {
      System.out.println("");
    } else if (actionResponse == ActionEvent.SUCCESS_DELETE) {
      System.out.println("Member successfully deleted");
    } else if (actionResponse == ActionEvent.ERR_FIND_MEMBER) {
      System.out.println("Could not find a member with this ID!");
    } else if (actionResponse == ActionEvent.ERR_FIND_ITEM) {
      System.out.println("Could not find an item with this ID!");
    }  else if (actionResponse == ActionEvent.ERR_CREATE_CONTRACT) {
      System.out.println("Could not set up lending contract!");
    }  else if (actionResponse == ActionEvent.SUCCESS_CREATE_CONTRACT) {
      System.out.println("Lending contract was successfully set up!");
    } 

  }
}
