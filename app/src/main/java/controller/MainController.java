package controller;

import java.util.ArrayList;
import model.domain.Item;
import model.domain.LendingContract;
import model.domain.Member;

/**
 * Wrapper class for controller-actions.
 *
 */
public class MainController {
  private view.Console ui;
  private model.domain.StuffLendingSystem sls;

  /**
   * The types of events that can occur in response to an action.
   *
   */
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
   * The types of prompts to use in communication with view.
   *
   */
  public static enum PromptEvent {
    ItemId,
    MemberId,
    FirstName,
    LastName,
    Email,
    PhoneNumber,
    Name,
    Description,
    CostPerDay,
    LoanStartDay,
    AmountOfLoanDays,
    ForwardDay
  }

  /**
   * Instaciate the MainController with a user interface representing the view and the main model class.
   *
   * @param ui - The view user interface. 
   * @param sls - The main class in the model.
   */
  public MainController(view.Console ui, model.domain.StuffLendingSystem sls) {
    // TODO: Supress findbugs as the ui and sls should be presented to the class.
    this.ui = ui;
    this.sls = sls;
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

      if (event == view.Console.MainEvent.MemberMenu) {
        doMemberMenu();
      }
      if (event == view.Console.MainEvent.ItemMenu) {
        doItemMenu();
      }
      if (event == view.Console.MainEvent.ForwardDay) {
        doForwardDayMenu();
      }
      if (event == view.Console.MainEvent.Quit) {
        running = false;
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
        model.domain.Member newMember = ui.promptForNewMember();

        boolean isSucceeded = sls.addNewMember(newMember);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_CREATE_MEMBER : ActionEvent.ERR_CREATE_MEMBER);
      }

      if (event == view.Console.MemberEvent.ListMember) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printMemberList(members, items);
      }

      if (event == view.Console.MemberEvent.ListMemberVerbose) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();

        for (Member.Mutable m : members) {
          Iterable<Item.Mutable> memberItems = sls.getItemByOwner(m);

          ui.printVerboseMember(m);
          
          for (Item.Mutable item : memberItems) {
            ArrayList<model.domain.LendingContract> expiredContracts = sls.getExpiredContractsByItem(item);
            ArrayList<model.domain.LendingContract> activeContracts = sls.getContractsByItem(item);
            ui.printDetailedItem(item, activeContracts, expiredContracts);
          }
        }
      }
      
      if (event == view.Console.MemberEvent.DetailedMember) {
        String id = ui.promptInformation(PromptEvent.MemberId);
        
        model.domain.Member member = sls.findMemberById(id);
        
        if (member != null) {
          ui.printDetailedMember(member);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
      }
      
      if (event == view.Console.MemberEvent.EditMember) {
        String id = ui.promptInformation(PromptEvent.MemberId);

        model.domain.Member.Mutable member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
        
      }
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        String id = ui.promptInformation(PromptEvent.MemberId);

        boolean isSucceeded = sls.deleteMember(id);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_DELETE : ActionEvent.ERR_DELETE);
      }

    } while (running);
  }

  private void doEditMemberMenu(model.domain.Member.Mutable member) {
    boolean running = true;

    do {
      ui.printDetailedMember(member);
      view.Console.MemberEditEvent event = ui.getEditMemberMenuChoice();

      if (event == view.Console.MemberEditEvent.EditFirstName) {
        String firstName =  ui.promptInformation(PromptEvent.FirstName);

        member.setFirstName(firstName);
      }

      if (event == view.Console.MemberEditEvent.EditLastName) {
        String lastName =  ui.promptInformation(PromptEvent.LastName);

        member.setLastName(lastName);
      }

      if (event == view.Console.MemberEditEvent.EditEmail) {
        String email =  ui.promptInformation(PromptEvent.Email);

        member.setEmail(email);
      }

      if (event == view.Console.MemberEditEvent.EditPhone) {
        String phoneNumber =  ui.promptInformation(PromptEvent.PhoneNumber);

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
        String memberId =  ui.promptInformation(PromptEvent.MemberId);
        model.domain.Member.Mutable member = sls.findMemberById(memberId);

        if (member != null) {
          model.domain.Item item = ui.promptForNewItem();
          sls.addNewItem(member, item);
          ui.actionResponder(ActionEvent.SUCCESS_CREATE_ITEM);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }

      } 

      if (event == view.Console.ItemEvent.ListItems) {
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printItemList(items);
      }

      if (event == view.Console.ItemEvent.DetailedItem) {
        String id =  ui.promptInformation(PromptEvent.ItemId);

        model.domain.Item item = sls.findItemById(id);

        if (item != null) {
          ArrayList<model.domain.LendingContract> expiredContracts = sls.getExpiredContractsByItem(item);
          ArrayList<model.domain.LendingContract> activeContracts = sls.getContractsByItem(item);
          ui.printDetailedItem(item, activeContracts, expiredContracts);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
        }
      } 

      if (event == view.Console.ItemEvent.EditItem) {
        String id = ui.promptInformation(PromptEvent.ItemId);

        model.domain.Item.Mutable item = sls.findItemById(id);

        if (item != null) {
          doEditItemMenu(item);

        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
        }
      }

      if (event == view.Console.ItemEvent.LendItem) {
        String itemId = ui.promptInformation(PromptEvent.ItemId);
        model.domain.Item item = sls.findItemById(itemId);

        if (item == null) {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
          return;
        }

        String lenderId = ui.promptInformation(PromptEvent.MemberId);
        model.domain.Member lender = sls.findMemberById(lenderId);

        if (lender == null) {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
          return;
        }

        int currentDay = sls.getCurrentDay();
        ui.notifyCurrentDay(sls.getCurrentDay());

        int startDayOfLoan = 0;
        do {
          startDayOfLoan = ui.promptInformationInt(PromptEvent.LoanStartDay); 
        } while (startDayOfLoan < currentDay);

        int daysToLoan = ui.promptInformationInt(PromptEvent.AmountOfLoanDays);
        int endDay = startDayOfLoan + daysToLoan;

        model.domain.LendingContract contract = new LendingContract(lender, endDay, item, startDayOfLoan);
        boolean successfullyCreatedContract = sls.setUpLendingContract(contract);
        if (successfullyCreatedContract) {
          ui.actionResponder(ActionEvent.SUCCESS_CREATE_CONTRACT);
        } else {
          ui.actionResponder(ActionEvent.ERR_CREATE_CONTRACT);
        }
      } 

      if (event == view.Console.ItemEvent.DeleteItem) {
        String itemId = ui.promptInformation(PromptEvent.ItemId);
        Boolean isSucceeded = sls.deleteItem(itemId);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_DELETE : ActionEvent.ERR_DELETE);
      } 

      if (event == view.Console.ItemEvent.Back) {
        running = false;
      } 
    } while (running);
  }

  private void doEditItemMenu(model.domain.Item.Mutable item) {
    boolean running = true;

    do {
      view.Console.ItemEditEvent event = ui.getEditItemMenuChoice();

      if (event == view.Console.ItemEditEvent.EditName) {
        String name = ui.promptInformation(PromptEvent.Name);

        item.setName(name);
      }
      
      if (event == view.Console.ItemEditEvent.EditDescription) {
        String description = ui.promptInformation(PromptEvent.Description);

        item.setDescription(description);
      }

      if (event == view.Console.ItemEditEvent.EditCost) {
        int cost = ui.promptInformationInt(PromptEvent.CostPerDay);

        item.setCostPerDay(cost);
      }

      if (event == view.Console.ItemEditEvent.Back) {
        running = false;
      }

    } while (running);
  }
  
  private void doForwardDayMenu() {
    int amountOfDaysToProceed = ui.promptInformationInt(PromptEvent.ForwardDay);
    
    for (int i = 0; i < amountOfDaysToProceed; i++) {
      sls.incrementCurrentDay();
    }

    ui.notifyCurrentDay(sls.getCurrentDay());
  }
}


