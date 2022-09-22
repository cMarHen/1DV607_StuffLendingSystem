package controller;

import java.util.ArrayList;

public class MainController {
  private view.Console ui;
  private model.domain.StuffLendingSystem sls;
  private int currentDay;

  public MainController(view.Console ui, model.domain.StuffLendingSystem sls) {
    this.ui = ui;
    this.sls = sls;
    this.currentDay = 0;
  }

  public void doMainMenu() {
    boolean running = true;
    do {
      view.Console.MainEvent event = ui.getMainMenuChoice();

      if (event == view.Console.MainEvent.Quit) {
        running = false;
      }

      if (event == view.Console.MainEvent.MemberMenu) {
        doMemberMenu();
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
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        String id = ui.promptForAnswer("Enter id for the user to be removed: ");

        boolean isSucceeded = sls.deleteMember(id);

        ui.printActionResponse(isSucceeded ? "Member successfully deleted" : "Could not find a member with this id!");
      }

    } while (running);
  }
}
