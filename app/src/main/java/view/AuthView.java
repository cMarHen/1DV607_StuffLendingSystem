package view;

import java.util.Scanner;
import model.domain.Item;
import model.domain.Member;
import view.MainView.MenuEvent;

/**
 * Class as authenticated user View.
 */
public class AuthView extends View {

  public AuthView(Scanner scan) {
    super(scan);
  }

  @Override
  public MenuEvent getMainMenuChoice() {
    AuthMainMenu mainMenu = new AuthMainMenu(this.scan);
    return mainMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getMemberMenuChoice() {
    AuthMemberMenu memberMenu = new AuthMemberMenu(this.scan);
    return memberMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getItemMenuChoice() {
    AuthItemMenu itemMenu = new AuthItemMenu(this.scan);
    return itemMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getEditMemberMenuChoice() {
    AuthEditMemberMenu editMemberMenu = new AuthEditMemberMenu(this.scan);
    return editMemberMenu.getMenuChoice();
  }

  @Override
  public MenuEvent getEditItemMenuChoice() {
    AuthEditItemMenu editItemMenu = new AuthEditItemMenu(this.scan);
    return editItemMenu.getMenuChoice();
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
  public String promptForItemName(boolean query) {
    return query
      ? promptForString("Search on full name, subset or blank: ")
      : promptForString("Enter item name: ");
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
    return promptForConfirmation("Are you sure you want to delete your account? ");
  }
}
