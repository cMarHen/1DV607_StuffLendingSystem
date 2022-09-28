package controller;

import java.util.ArrayList;
import model.domain.Item;

/**
 * Wrapper class for controller-actions.
 *
 */
public class MainController {
  private view.Console ui;
  private model.domain.StuffLendingSystem sls;
  private CurrentDay currentDay;

  public static enum ActionEvent {
    ERR_CREATE_MEMBER,
    SUCCESS_CREATE_MEMBER,
    ERR_CREATE_ITEM,
    SUCCESS_CREATE_ITEM,
    ERR_EDIT_MEMBER,
    SUCCESS_EDIT_MEMBER,
    ERR_EDIT_ITEM,
    SUCCESS_EDIT_ITEM,
    ERR_DELETE,
    SUCCESS_DELETE,
    ERR_FIND_MEMBER,
    ERR_FIND_ITEM,
    SUCCESS_CREATE_CONTRACT,
    ERR_CREATE_CONTRACT
  }

  /**
   * Instaciate the MainController with a user interface representing the view and the main model class.
   *
   * @param ui - The view user interface. 
   * @param sls - The main class in the model.
   */
  public MainController(view.Console ui, model.domain.StuffLendingSystem sls) {
    this.ui = ui;
    this.sls = sls;
    this.currentDay = new CurrentDay();
    // currentRole = enum.Role
    // currentId = id
  }

  /**
   * Trigger the output of main-menu in the ui(view), this method runs until Quit-event is emitted from the view.
   *
   */
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
        /* memberController. */doMemberMenu();
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

        boolean isSucceeded = sls.addNewMember(firstName, lastName, email, phoneNumber, currentDay.getCurrentDay(), 0);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_CREATE_MEMBER : ActionEvent.ERR_CREATE_MEMBER);
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
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
      }
      
      if (event == view.Console.MemberEvent.EditMember) {
        String id = ui.promptForAnswer("Enter id for the user to edit: ");

        model.domain.Member member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
        
      }
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        String id = ui.promptForAnswer("Enter id for the user to be removed: ");

        boolean isSucceeded = sls.deleteMember(id);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_DELETE : ActionEvent.ERR_FIND_MEMBER);
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

      if (event == view.Console.ItemEvent.AddItem) {
        String memberId = ui.promptForAnswer("Enter member id (owner): ");
        model.domain.Member member = sls.findMemberById(memberId);

        if (member != null) {
          model.domain.Item.ItemType type = ui.getItemTypeMenuChoice();
          // TODO: Måste kunna läsa HEEEELA raden!
          String name = ui.promptForAnswer("Enter the name: ");

          // TODO: Måste kunna läsa HEEEELA raden!
          String description = ui.promptForAnswer("Enter description: ");
          int costPerDay = 50; // TODO: Implement prompt for cost per day.

          boolean isSucceeded = sls.addNewItem(
              memberId,
              type,
              name,
              description,
              currentDay.getCurrentDay(),
              costPerDay);

          ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_CREATE_ITEM : ActionEvent.ERR_CREATE_ITEM);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }

      } 

      if (event == view.Console.ItemEvent.ListItems) {
        ArrayList<model.domain.Item> items = sls.getAllItems();

        ui.printItemList(items);
      }

      if (event == view.Console.ItemEvent.DetailedItem) {
        String id = ui.promptForAnswer("Enter the item ID: ");

        Item item = sls.findItemById(id);

        if (item != null) {
          ui.printDetailedItem(item);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
        }
      } 

      if (event == view.Console.ItemEvent.LendItem) {
        String itemId = ui.promptForAnswer("Enter the item ID: ");
        Item item = sls.findItemById(itemId);

        if (item == null) {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
          return;
        }

        String lenderId = ui.promptForAnswer("Enter the lenders ID: ");
        model.domain.Member lender = sls.findMemberById(lenderId);

        if (lender == null) {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
          return;
        }

        int daysToLoan = ui.promptForIntAnswer("Number of days to loan the item: ");
        int endDay = currentDay.getCurrentDay() + daysToLoan;

        boolean successfullyCreatedContract = 
            sls.setUpLendingContract(lenderId, endDay, itemId, currentDay.getCurrentDay());

        if (successfullyCreatedContract) {
          ui.actionResponder(ActionEvent.SUCCESS_CREATE_CONTRACT);
        } else {
          ui.actionResponder(ActionEvent.ERR_CREATE_CONTRACT);
        }
      } 

      if (event == view.Console.ItemEvent.DeleteItem) {
        // TODO: Implement.
      } 

      if (event == view.Console.ItemEvent.Back) {
        running = false;
      } 
    } while (running);
  }
}
