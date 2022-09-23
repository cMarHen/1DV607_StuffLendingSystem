package controller;

import java.util.ArrayList;

import model.domain.Item;

public class MainController {
  private view.Console ui;
  private model.domain.StuffLendingSystem sls;
  private int currentDay;

  public MainController(view.Console ui, model.domain.StuffLendingSystem sls) {
    this.ui = ui;
    this.sls = sls;
    this.currentDay = 0;
    // currentRole = enum.Role
    // currentId = id
  }

  public void doMainMenu() {
    boolean running = true;
    do {
      view.Console.MainEvent event = ui.getMainMenuChoice();
      // view.Console.MainEvent event = ui.getNotLoggedMainMenuChoice();
      // view.Console.MainEvent event = ui.getAdminMainMenuChoice();

      if (event == view.Console.MainEvent.Quit) {
        running = false;
      }

      if (event == view.Console.MainEvent.MemberMenu) {
        doMemberMenu();
      }

      if (event == view.Console.MainEvent.ItemMenu) {
        doItemMenu();
      }
    } while (running);
  }

  private void doMemberMenu() {
    boolean running = true;

    do {
      view.Console.MemberEvent event = ui.getMemberMenuChoice();

      if (event == view.Console.MemberEvent.Back) {
        return;
      }
      
      if (event == view.Console.MemberEvent.AddMember) {
        String firstName = ui.promptForAnswer("Enter your first name: ");
        String lastName = ui.promptForAnswer("Enter your last name: ");
        String email = ui.promptForAnswer("Enter your email: ");
        String phoneNumber = ui.promptForAnswer("Enter your phone number: ");

        boolean isSucceeded = sls.addNewMember(firstName, lastName, email, phoneNumber, currentDay);

        ui.printActionResponse(isSucceeded ? "Member successfully created" : "Member could not be created!");
      }

      if (event == view.Console.MemberEvent.ListMember) {
        ArrayList<model.domain.Member> members = sls.getMembers();

        ui.printMemberList(members);
      }
      
      if (event == view.Console.MemberEvent.DetailedMember) {
        String id = ui.promptForAnswer("Enter the member ID: ");
        
        model.domain.Member member = sls.findMemberById(id);
        
        if (member != null) {
          ui.printDetailedMember(member);
        } else {
          ui.printActionResponse("Could not find a member with this ID. ");
        }
      }
      
      if (event == view.Console.MemberEvent.EditMember) {
        String id = ui.promptForAnswer("Enter id for the user to edit: ");

        model.domain.Member member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.printActionResponse("Could not find a member with this ID. ");
        }
        
      }
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        String id = ui.promptForAnswer("Enter id for the user to be removed: ");

        boolean isSucceeded = sls.deleteMember(id);

        ui.printActionResponse(isSucceeded ? "Member successfully deleted" : "Could not find a member with this id!");
      }

    } while (running);
  }

  private void doEditMemberMenu(model.domain.Member member) {
    boolean running = true;

    do {
      ui.printDetailedMember(member);
      view.Console.MemberEditEvent event = ui.getEditMemberMenuChoice();

      if (event == view.Console.MemberEditEvent.EditFirstName) {
        String firstName = ui.promptForAnswer("Enter the new first name: ");

        member.setFirstName(firstName);
      }

      if (event == view.Console.MemberEditEvent.EditLastName) {
        String lastName = ui.promptForAnswer("Enter the new last name: ");

        member.setLastName(lastName);
      }

      if (event == view.Console.MemberEditEvent.EditEmail) {
        String email = ui.promptForAnswer("Enter the new email: ");

        member.setEmail(email);
      }

      if (event == view.Console.MemberEditEvent.EditPhone) {
        String phoneNumber = ui.promptForAnswer("Enter the new phone number: ");

        member.setPhoneNumber(phoneNumber);
      }

      if (event == view.Console.MemberEditEvent.Back) {
        running = false;
      }

    } while (running);
  }

  private void doItemMenu() {
    boolean running = true;

    do {
      view.Console.ItemEvent event = ui.getItemMenuChoice();

      if (event == view.Console.ItemEvent.ListItems) {
        ArrayList<model.domain.Item> items = sls.getItems();

        ui.printItemList(items);
      }
    } while (running);
  }

}
