package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MainController.ActionEvent;
import controller.MainController.PromptEvent;
import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.Item.Mutable;
import model.domain.LendingContract;
import model.domain.Member;
import view.MainView.MenuEvent;

public class UnAuthView implements View {
  private Scanner scan;

  public UnAuthView(Scanner scan) {
    this.scan = scan;
  }

  @Override
  public MenuEvent getMainMenuChoice() {
    MainMenu mainMenu = new MainMenu(this.scan);
    return mainMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getMemberMenuChoice() {
    MemberMenu memberMenu = new MemberMenu(this.scan);
    return memberMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getItemMenuChoice() {
    ItemMenu itemMenu = new ItemMenu(this.scan);
    return itemMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getEditMemberMenuChoice() {
    // TODO: Throw error!
    return null;
  }

  @Override
  public MenuEvent getEditItemMenuChoice() {
    // TODO: Throw error!
    return null;
  }

  @Override
  public ItemType getItemTypeMenuChoice() {
        // TODO: Throw error!
    return null;
  }

  @Override
  public void printMemberList(Iterable<? extends Member> members, Iterable<Mutable> items) {
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("                                     Member list     %n");
    System.out.printf("                                     ( Simple )       %n");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf(
        "| %-20s | %-20s | %-8s | %-8s | %-8s | %-6s |%n", "NAME", "EMAIL", "PHONE", "CREDITS", "ITEMS", "ID");
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
      
      System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s | %-6s |%n",
          member.getFirstName() + " " + member.getLastName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getCredits(),
          ownedItems.size(),
          member.getId());
    }
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
  }

  @Override
  public void printVerboseMember(model.domain.Member.Mutable member) {
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("                     DETAILED MEMBER: " + member.getFirstName() + "     %n");
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s |%n", "NAME", "EMAIL", "PHONE", "CREDITS", "ID");
    System.out.printf("| %-20s | %-20s | %-8s | %-8s | %-8s |%n",
        member.getFirstName() + " " + member.getLastName(),
        member.getEmail(),
        member.getPhoneNumber(),
        member.getCredits(),
        member.getId());
    System.out.println();
    System.out.printf("_________________________________________________________________________________________%n");
    System.out.println();
    
  }

  @Override
  public void printItemList(Iterable<? extends Item> items) {
    if (!items.iterator().hasNext()) {
      System.out.println("No items to show.");
    }

    System.out.printf(
        "______________________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("                                     Item list     %n");
    System.out.printf(
        "______________________________________________________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-40s | %-10s | %-14s | %-10s | %-10s |%n", "NAME", "TYPE", "COST/DAY", "AVAILABLE", "ID");
    System.out.printf(
        "______________________________________________________________________________________________________%n");
    System.out.println();

    for (model.domain.Item item : items) {
      System.out.printf("| %-40s | %-10s | %-14s | %-10s | %-10s |%n",
          item.getName(),
          item.getType(),
          item.getCostPerDay(),
          (item.getIsReserved() ? "no" : "yes"),
          item.getId());
    }
    System.out.println();
    System.out.printf(
        "______________________________________________________________________________________________________%n");
    System.out.println();

  }

  @Override
  public void printDetailedMember(Member member) {
    System.out.printf(
        "______________________________________________________"
        + "_______________________________________________________________%n");
    System.out.println();
    System.out.printf(" Member Details   %n");
    System.out.printf(
        "______________________________________________________"
        + "_______________________________________________________________%n");
    System.out.println();
    System.out.printf(
        "| %-40s | %-20s | %-20s | %-10s | %-10s |%n",
        "Name", "Email", "Phone", "Credits", "Id"
    );
    System.out.printf(
          "______________________________________________________"
          + "_______________________________________________________________%n");
    System.out.println();
    System.out.printf("| %-40s | %-20s | %-20s | %-10s | %-10s |%n",
        member.getFirstName() + " " + member.getLastName(),
        member.getEmail(),
        member.getPhoneNumber(),
        member.getCredits(),
        member.getId());
    System.out.println();
  }

  @Override
  public void printDetailedItem(Item item, ArrayList<LendingContract> activeContracts,
      ArrayList<LendingContract> expiredContracts) {
        System.out.printf("_________________________________________________________________________________________%n");
        System.out.println();
        System.out.printf(" ITEM DETAILS: " + item.getName()  + "                                %n");
        System.out.printf("_________________________________________________________________________________________%n");
        System.out.println();
        System.out.printf(
            "| %-20s | %-20s | %-10s | %-40s | %-10s | %-10s |%n",
            "Id", "Name", "Type", "Description", "Cost/Day", "Available"
        );
        System.out.printf(
            "______________________________________________________"
            + "_______________________________________________________________%n");
        System.out.printf("| %-20s | %-20s | %-10s | %-40s | %-10s | %-10s |%n",
            item.getId(),
            item.getName(),
            item.getType(),
            item.getDescription(),
            item.getCostPerDay(), 
            (item.getIsReserved() ? "no" : "yes"));
        System.out.println();
        
        System.out.printf(" Active Contracts                                                                         %n");
        System.out.printf("----------------%n");
        System.out.printf(
            "| %-10s | %-10s | %-30s | %-10s | %-10s |%n", "Start day", "End day", "Lender", "Lender ID", "Contract fee");
        System.out.printf("---------------------------------------------------------------------------------------%n");
        
        if (activeContracts.size() != 0) {
          Collections.sort(activeContracts);
          for (model.domain.LendingContract contract : activeContracts) {
            System.out.printf("| %-10s | %-10s | %-30s | %-10s | %-10s |%n",
                contract.getStartDay(), contract.getEndDay(), 
                contract.getLender().getFirstName() + " " + contract.getLender().getLastName(),
                contract.getLender().getId(),
                contract.getTotalContractFee());
          }
          System.out.printf("---------------------------------------------------------------------------------------%n");
        }
        System.out.println();
        System.out.printf(" Expired Contracts                                                                        %n");
        System.out.printf("----------------%n");
        System.out.printf(
            "| %-10s | %-10s | %-30s | %-10s | %-10s |%n", "Start day", "End day", "Lender", "Lender ID", "Contract fee");
        System.out.printf("---------------------------------------------------------------------------------------%n");
        
        if (expiredContracts.size() != 0) {
          Collections.sort(expiredContracts, Collections.reverseOrder());
          for (model.domain.LendingContract contract : expiredContracts) {
            System.out.printf("| %-10s | %-10s | %-30s | %-10s | %-10s |%n",
                contract.getStartDay(),
                contract.getEndDay(),
                contract.getLender().getFirstName() + " " + contract.getLender().getLastName(),
                contract.getLender().getId(),
                contract.getTotalContractFee());
          }
          System.out.printf("---------------------------------------------------------------------------------------%n");
        }
    
        System.out.println();
      }

  @Override
  public String promptInformation(PromptEvent event) {
    if (event == PromptEvent.ItemId) {
      return promptForString("Enter the item ID: ");
    } else if (event == PromptEvent.MemberId) {
      return promptForString("Enter the member ID: ");
    } else if (event == PromptEvent.FirstName) {
      return promptForString("Enter first name: ");
    } else if (event == PromptEvent.LastName) {
      return promptForString("Enter last name: ");
    } else if (event == PromptEvent.Email) {
      return promptForString("Enter email: ");
    } else if (event == PromptEvent.PhoneNumber) {
      return promptForString("Enter phone number: ");
    } else if (event == PromptEvent.Name) {
      return promptForString("Enter name: ");
    } else if (event == PromptEvent.Description) {
      return promptForString("Enter description: ");
    }

    return null;
  }

  @Override
  public int promptInformationInt(PromptEvent event) {
    if (event == PromptEvent.CostPerDay) {
      return promptForInt("Enter the cost per day: ");
    } else if (event == PromptEvent.LoanStartDay) {
      return promptForInt("From which day do you want to book this item?: ");
    } else if (event == PromptEvent.AmountOfLoanDays) {
      return promptForInt("Number of days to loan the item: ");
    } else if (event == PromptEvent.ForwardDay) {
      return promptForInt("How many days do you want to proceed?: ");
    } 
    
    return 0;
  }

  @Override
  public void actionResponder(ActionEvent actionResponse) {
    if (actionResponse == ActionEvent.ERR_CREATE_MEMBER) {
      System.out.println("Member could not be created!");
    } else if (actionResponse == ActionEvent.SUCCESS_CREATE_MEMBER) {
      System.out.println("Member successfully created!");
    }  else if (actionResponse == ActionEvent.ERR_CREATE_ITEM) {
      System.out.println("Item could not be created!");
    } else if (actionResponse == ActionEvent.SUCCESS_CREATE_ITEM) {
      System.out.println("Item successfully created!");
    } else if (actionResponse == ActionEvent.ERR_DUPLICATE_EMAIL) {
      System.out.println("This email is already in use!");
    } else if (actionResponse == ActionEvent.ERR_DUPLICATE_PHONE) {
      System.out.println("This phonenumber is already in use!");
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

  @Override
  public Member promptForNewMember() {
    String firstName = promptForString("Enter first name: ");
    String lastName = promptForString("Enter last name: ");
    String email = promptForString("Enter email: ");
    String phoneNumber = promptForString("Enter phone number: ");

    return new model.domain.Member(firstName, lastName, email, phoneNumber);
  }

  @Override
  public Item promptForNewItem() {
    model.domain.Item.ItemType type = getItemTypeMenuChoice();

    String name =  promptForString("Enter name: ");
    String description =  promptForString("Enter description: ");
    // TODO: check for positive int.
    int costPerDay = promptForInt("Enter cost per day: ");

    return new model.domain.Item(type, name, description, costPerDay);
  }

  @Override
  public void notifyCurrentDay(int currentDay) {
    System.out.println("The current day is: " + currentDay);
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
