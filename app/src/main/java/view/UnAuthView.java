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

  @Override
  public boolean promptDeleteMemberConfirmation() {
    // TODO: Thorow error!
    return true;
  }
}
