package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.Item.Mutable;
import model.domain.LendingContract;
import model.domain.Member;
import view.MainView.MenuEvent;

public class UnAuthView extends View {

  public UnAuthView(Scanner scan) {
    super(scan);
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
  public String promptForPassword() {
    return promptForString("Enter password: ");
  }

  @Override
  public void notifyCurrentDay(int currentDay) {
    System.out.println("The current day is: " + currentDay);
  }

  @Override
  public String promptForItemId() {
    return promptForString("Enter the item ID: ");
  }

  @Override
  public String promptForMemberId() {
    return promptForString("Enter the member ID: ");
  }

  @Override
  public String promptForFirstName() {
    return promptForString("Enter first name: ");
  }

  @Override
  public String promptForLastName() {
    return promptForString("Enter last name: ");
  }

  @Override
  public String promptForEmail() {
    return promptForString("Enter email: ");
  }

  @Override
  public String promptForPhone() {
    return promptForString("Enter phone number: ");
  }

  @Override
  public String promptForItemName() {
    return promptForString("Enter item name: ");
  }

  @Override
  public String promptForItemDescription() {
    return promptForString("Enter description: ");
  }

  @Override
  public int promptForCostPerDay() {
    return promptForInt("Enter the cost per day: ");
  }

  @Override
  public int promptForLoanStartDay() {
    return promptForInt("From which day do you want to book this item?: ");
  }

  @Override
  public int promptForDaysToLoan() {
    return promptForInt("Number of days to loan the item: ");
  }

  @Override
  public int promptForDaysToProceed() {
    return promptForInt("How many days do you want to proceed?: ");
  }
}
