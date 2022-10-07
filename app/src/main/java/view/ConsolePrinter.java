package view;

import java.util.ArrayList;
import java.util.Collections;
import model.domain.Item;
import model.domain.Item.Mutable;
import model.domain.LendingContract;
import model.domain.Member;

/**
 * Uses the console to print messages.
 *
 */
public class ConsolePrinter implements Printer {
  @Override
  public void printCurrentDay(int currentDay) {
    System.out.println("The current day is: " + currentDay);
  }

  @Override
  public void printCreateMemberSuccess() {
    System.out.println("Member successfully created!");
  }

  @Override
  public void printCreateMemberError() {
    System.out.println("Member could not be created!");
  }

  @Override
  public void printCreateItemSuccess() {
    System.out.println("Item successfully created!");
  }

  @Override
  public void printCreateItemError() {
    System.out.println("Item could not be created!");
  }

  @Override
  public void printDeleteMemberSuccess() {
    System.out.println("Successfully deleted member!");
  }

  @Override
  public void printDeleteMemberError() {
    System.out.println("Could not delete this member!");
  }

  @Override
  public void printDeleteItemSuccess() {
    System.out.println("Successfully deleted item!");
  }

  @Override
  public void printDeleteItemError() {
    System.out.println("Could not delete this item!");
  }

  @Override
  public void printCreateContractSuccess() {
    System.out.println("Lending contract was successfully set up!");
  }

  @Override
  public void printCreateContractError() {
    System.out.println("Could not set up lending contract!");
  }

  @Override
  public void printFindMemberError() {
    System.out.println("Could not find a member with this ID!");
  }

  @Override
  public void printFindItemError() {
    System.out.println("Could not find an item with this ID!");
  }

  @Override
  public void printDuplicateEmail() {
    System.out.println("This email is already in use!");
  }

  @Override
  public void printDuplicatePhone() {
    System.out.println("This phonenumber is already in use!");
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
        "| %-10s | %-10s | %-30s | %-10s | %-10s |%n",
        "Start day", "End day", "Lender", "Lender ID", "Contract fee");
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
        "| %-10s | %-10s | %-30s | %-10s | %-10s |%n",
        "Start day", "End day", "Lender", "Lender ID", "Contract fee");
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
}
