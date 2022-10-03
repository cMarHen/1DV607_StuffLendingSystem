package view;

import controller.MainController.ActionEvent;
import controller.MainController.promptEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import model.domain.Item;
import model.domain.Item.ItemType;


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
   * Events emmited from the class that constructs the edit item-menu choices.
   *
   */
  public static enum ItemEditEvent {
    EditName,
    EditDescription,
    EditCost,
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

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MainEvent.values().length || choice < 0);


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

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MemberEvent.values().length || choice < 0);

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

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MemberEditEvent.values().length || choice < 0);

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

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= ItemEvent.values().length || choice < 0);

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
   * Prints the edit-item menu and emits event based on users choice.
   *
   * @return - Matching event for users choice.
   */
  public ItemEditEvent getEditItemMenuChoice() {
    System.out.println("------------------------");
    System.out.println("- Edit Item ---------- -");
    System.out.println("------------------------");
    System.out.println("1. Edit name");
    System.out.println("2. Edit description");
    System.out.println("3. Edit cost per day");
    System.out.println("0. Back ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= ItemEditEvent.values().length || choice < 0);

    if (choice == 1) {
      return ItemEditEvent.EditName;
    } else if (choice == 2) {
      return ItemEditEvent.EditDescription;
    } else if (choice == 3) {
      return ItemEditEvent.EditCost;
    } else {
      return ItemEditEvent.Back;
    }
  }

  /**
   * Prints menu based on the available item types reads the user choice of type.
   *
   * @return - The users item-type choice.
   */
  public model.domain.Item.ItemType getItemTypeMenuChoice() {
    for (int i = 1; i <= model.domain.Item.ItemType.values().length; i++) {
      System.out.println(i + ". " + model.domain.Item.ItemType.values()[i - 1]);
    }

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= ItemType.values().length || choice < 0);

    return model.domain.Item.ItemType.values()[choice - 1];
  }

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public void printMemberList(
      Iterable<? extends model.domain.Member> members,
      Iterable<model.domain.Item.Mutable> items
  ) {
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("                                     Member list     %n");
    System.out.printf("                                     ( Simple )       %n");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-6s |%n", "NAME", "EMAIL", "CREDITS", "ITEMS", "ID");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    for (model.domain.Member member : members) {
      ArrayList<Item.Mutable> ownedItems = new ArrayList<>();
      for (Item.Mutable item : items) {
        String memberId = member.getId();
        if (item.getOwner().getId().equals(memberId)) {
          ownedItems.add(item);
        }
      }
      
      System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-6s |%n",
          member.getFirstName() + " " + member.getLastName(),
          member.getEmail(),
          member.getCredits(),
          ownedItems.size(),
          member.getId());
    }
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
  }

  /**
   * Prints the list of all members with name, day of registration and id.
   *
   */
  public void printVerboseMemberList(
      Iterable<? extends model.domain.Member> members,
      Iterable<model.domain.Item.Mutable> items
  ) {
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("                                     Member list     %n");
    System.out.printf("                                     ( Simple )       %n");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-6s |%n", "NAME", "EMAIL", "CREDITS", "ITEMS", "ID");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    for (model.domain.Member member : members) {
      ArrayList<Item.Mutable> ownedItems = new ArrayList<>();
      for (Item.Mutable item : items) {
        String memberId = member.getId();
        if (item.getOwner().getId().equals(memberId)) {
          ownedItems.add(item);
        }
      }
      
      System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-6s |%n",
          member.getFirstName() + " " + member.getLastName(),
          member.getEmail(),
          member.getCredits(),
          ownedItems.size(),
          member.getId());
    }
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
  }
  
  /**
   * Prints the list of all items with name, type, availibility-status and id.
   * If no items in the list a message is printed to signal this to the user.
   *
   */
  public void printItemList(Iterable<? extends model.domain.Item> items) {
    if (!items.iterator().hasNext()) {
      System.out.println("No items to show.");
    }
    
    for (model.domain.Item item : items) {
      System.out.println(" --- ");
      System.out.println("Name: " + item.getName());
      System.out.println("Type: " + item.getType());
      System.out.println("Available: " + (item.getIsReserved() ? "no" : "yes"));
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
  public void printDetailedItem(model.domain.Item item, ArrayList<model.domain.LendingContract> activeContracts, ArrayList<model.domain.LendingContract> expiredContracts) {
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf(" Item Details                                                                                %n");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-20s | %-10s | %-40s | %-10s | %-10s |%n", "Name", "Type", "Description", "Cost/Day", "Available");
    System.out.printf("----------------------------------------------------------------------------------------------------%n");
    System.out.printf("| %-20s | %-10s | %-40s | %-10s | %-10s |%n", item.getName(), item.getType(), item.getDescription(), item.getCostPerDay(), (item.getIsReserved() ? "no" : "yes"));
    System.out.println();
    
    System.out.printf(" Active Contracts                                                                            %n");
    System.out.printf("----------------%n");
    System.out.printf("| %-10s | %-10s | %-30s | %-10s |%n", "Start day", "End day", "Lender", "Contract fee");
    System.out.printf("---------------------------------------------------------------------------%n");
    
    if (activeContracts.size() != 0) {
      Collections.sort(activeContracts);
      for (model.domain.LendingContract contract : activeContracts) {
        System.out.printf("| %-10s | %-10s | %-30s | %-10s |%n", contract.getStartDay(), contract.getEndDay(), contract.getLender().getFirstName() + " " + contract.getLender().getLastName(), contract.getTotalContractFee());
      }
      System.out.printf("---------------------------------------------------------------------------%n");
    }
    System.out.println();
    System.out.printf(" Expired Contracts                                                                            %n");
    System.out.printf("----------------%n");
    System.out.printf("| %-10s | %-10s | %-30s | %-10s |%n", "Start day", "End day", "Lender", "Contract fee");
    System.out.printf("---------------------------------------------------------------------------%n");
    
    if (expiredContracts.size() != 0) {
      Collections.sort(expiredContracts, Collections.reverseOrder());
      for (model.domain.LendingContract contract : expiredContracts) {
        System.out.printf("| %-10s | %-10s | %-30s | %-10s |%n", contract.getStartDay(), contract.getEndDay(), contract.getLender().getFirstName() + " " + contract.getLender().getLastName(), contract.getTotalContractFee());
      }
      System.out.printf("---------------------------------------------------------------------------------------%n");
    }

    System.out.println();
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
  }

  public String promptInformation(promptEvent event) {
    if (event == promptEvent.ItemId) {
      return promptForString("Enter the item ID: ");
    } else if (event == promptEvent.MemberId) {
      return promptForString("Enter the member ID: ");
    } else if (event == promptEvent.FirstName) {
      return promptForString("Enter first name: ");
    } else if (event == promptEvent.LastName) {
      return promptForString("Enter last name: ");
    } else if (event == promptEvent.Email) {
      return promptForString("Enter email: ");
    } else if (event == promptEvent.PhoneNumber) {
      return promptForString("Enter phone number: ");
    } else if (event == promptEvent.Name) {
      return promptForString("Enter name: ");
    } else if (event == promptEvent.Description) {
      return promptForString("Enter description: ");
    }
      
    return null;
  }

  public int promptInformationInt(promptEvent event) {
    if (event == promptEvent.CostPerDay) {
      return promptForInt("Enter the cost per day: ");
    } else if (event == promptEvent.LoanStartDay) {
      return promptForInt("From which day do you want to book this item?: ");
    } else if (event == promptEvent.AmountOfLoanDays) {
      return promptForInt("Number of days to loan the item: ");
    } else if (event == promptEvent.ForwardDay) {
      return promptForInt("How many days do you want to proceed?: ");
    } 
    
    // TODO: Handle this..
    return 0;
  }

  /**
   * Prints a message to the user based on action-event.
   * (E.g, member not found, unable to set up lending contract etc.)
   *
   * @param actionResponse - The response from controller of type enum ActionEvent.
   */
  public void actionResponder(controller.MainController.ActionEvent actionResponse) {

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
      System.out.println("Oops, something went wrong. Could not delete this resource!");
    } else if (actionResponse == ActionEvent.SUCCESS_DELETE) {
      System.out.println("The resource was successfully deleted");
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

  /**
   * Request user-inputs needed to register a new member.
   *
   * @return - Readonly object with member-information.
   */
  public model.domain.Member promptForNewMember() {
    String firstName = promptForString("Enter first name: ");
    String lastName = promptForString("Enter last name: ");
    String email = promptForString("Enter email: ");
    String phoneNumber = promptForString("Enter phone number: ");

    return new model.domain.Member(firstName, lastName, email, phoneNumber);
  }

  /**
   * Request user-inputs needed to add a new item.
   *
   * @return - Readonly object with item-information.
   */
  public model.domain.Item promptForNewItem() {
    model.domain.Item.ItemType type = getItemTypeMenuChoice();

    String name =  promptForString("Enter name: ");
    String description =  promptForString("Enter description: ");
    // TODO: check for positive int.
    int costPerDay = promptForInt("Enter cost per day: ");

    return new model.domain.Item(type, name, description, costPerDay);
  }

  private int promptForInt(String message) {
    int input = -1;
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextInt();
        scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        scan.nextLine();
        validInput = false;
      }
    } while (!validInput);

    return input;
  }

  private String promptForString(String message) {
    String input = "";
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        validInput = false;
      }
    } while (!validInput);

    return input;
  }

	public void notifyCurrentDay(int currentDay) {
    System.out.println("The current day is: " + currentDay);
	}
}
