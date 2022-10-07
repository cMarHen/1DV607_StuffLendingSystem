package view;

import java.util.Scanner;
import model.domain.Item.ItemType;


/**
 * Main class and the Facade for the view.
 * 
 */
public class Console {
  private Scanner scan;
  public Printer printer;

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
    ListMemberVerbose,
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
    this.printer = new ConsolePrinter();
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
    System.out.println("2. List Members simple");
    System.out.println("3. List Members verbose");
    System.out.println("4. Show detailed member");
    System.out.println("5. Edit a member");
    System.out.println("6. Delete Member");
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
      return MemberEvent.ListMemberVerbose;
    } else if (choice == 4) {
      return MemberEvent.DetailedMember;
    } else if (choice == 5) {
      return MemberEvent.EditMember;
    } else if (choice == 6) {
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
    // TODO: Take Item as argument and print item details.
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

  public String promptForItemId() {
    return promptForString("Enter the item ID: ");
  }

  public String promptForMemberId() {
    return promptForString("Enter the member ID: ");
  }

  public String promptForFirstName() {
    return promptForString("Enter first name: ");
  }

  public String promptForLastName() {
    return promptForString("Enter last name: ");
  }

  public String promptForEmail() {
    return promptForString("Enter email: ");
  }

  public String promptForPhone() {
    return promptForString("Enter phone number: ");
  }

  public String promptForItemName() {
    return promptForString("Enter item name: ");
  }

  public String promptForItemDescription() {
    return promptForString("Enter description: ");
  }

  public int promptForCostPerDay() {
    return promptForInt("Enter the cost per day: ");
  }

  public int promptForLoanStartDay() {
    return promptForInt("From which day do you want to book this item?: ");
  }

  public int promptForDaysToLoan() {
    return promptForInt("Number of days to loan the item: ");
  }

  public int promptForDaysToProceed() {
    return promptForInt("How many days do you want to proceed?: ");
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
}
