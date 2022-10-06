package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item;
import model.domain.LendingContract;
import model.domain.Member;

/**
 * Wrapper class for controller-actions.
 *
 */
public class MainController {
  private view.MainView ui;
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
    ERR_CREATE_CONTRACT,
    ERR_DUPLICATE_EMAIL,
    ERR_DUPLICATE_PHONE
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
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "ui and sls should be a reference.")
  public MainController(view.MainView ui, model.domain.StuffLendingSystem sls) {
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
    try {
      boolean running = true;
      do {
        view.MainView.MenuEvent event = ui.getMainMenuChoice();

        if (event == view.MainView.MenuEvent.MemberMenu) {
          doMemberMenu();
        }
        if (event == view.MainView.MenuEvent.ItemMenu) {
          doItemMenu();
        }
        if (event == view.MainView.MenuEvent.ForwardDay) {
          doForwardDayMenu();
        }
        if (event == view.MainView.MenuEvent.Quit) {
          running = false;
        }
      } while (running);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void doMemberMenu() {
    boolean running = true;

    do {
      view.MainView.MenuEvent event = ui.getMemberMenuChoice();

      if (event == view.MainView.MenuEvent.Back) {
        return;
      }
      
      if (event == view.MainView.MenuEvent.AddMember) {
        boolean addMemberRunning = true;

        do {
          model.domain.Member newMember = ui.promptForNewMember();
  
          boolean isUniqueEmail = sls.isUniqueEmail(newMember.getEmail());
  
          if (!isUniqueEmail) {
            ui.actionResponder(ActionEvent.ERR_DUPLICATE_EMAIL);
            break;
          }
  
          boolean isUniquePhoneNumber = sls.isUniquePhoneNumber(newMember.getPhoneNumber());
  
          if (!isUniquePhoneNumber) {
            ui.actionResponder(ActionEvent.ERR_DUPLICATE_PHONE);
            break;
          }
  
          boolean isSucceeded = sls.addNewMember(newMember);
  
          ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_CREATE_MEMBER : ActionEvent.ERR_CREATE_MEMBER);

          addMemberRunning = false;
        } while (addMemberRunning);
      }

      if (event == view.MainView.MenuEvent.ListMember) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printMemberList(members, items);
      }

      if (event == view.MainView.MenuEvent.ListMemberVerbose) {
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
      
      if (event == view.MainView.MenuEvent.DetailedMember) {
        String id = ui.promptInformation(PromptEvent.MemberId);
        
        model.domain.Member member = sls.findMemberById(id);
        
        if (member != null) {
          ui.printDetailedMember(member);
        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
      }
      
      if (event == view.MainView.MenuEvent.EditMember) {
        String id = ui.promptInformation(PromptEvent.MemberId);

        model.domain.Member.Mutable member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_MEMBER);
        }
        
      }
      
      if (event == view.MainView.MenuEvent.DeleteMember) {
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
      view.MainView.MenuEvent event = ui.getEditMemberMenuChoice();

      if (event == view.MainView.MenuEvent.EditFirstName) {
        String firstName =  ui.promptInformation(PromptEvent.FirstName);

        member.setFirstName(firstName);
      }

      if (event == view.MainView.MenuEvent.EditLastName) {
        String lastName =  ui.promptInformation(PromptEvent.LastName);

        member.setLastName(lastName);
      }

      if (event == view.MainView.MenuEvent.EditEmail) {
        String email =  ui.promptInformation(PromptEvent.Email);
        boolean isUniqueEmail = sls.isUniqueEmail(email);

        if (!isUniqueEmail) {
          ui.actionResponder(ActionEvent.ERR_DUPLICATE_EMAIL);
        } else {
          member.setEmail(email);
        }
      }

      if (event == view.MainView.MenuEvent.EditPhone) {
        String phoneNumber =  ui.promptInformation(PromptEvent.PhoneNumber);
        boolean isUniquePhone = sls.isUniquePhoneNumber(phoneNumber);

        if (!isUniquePhone) {
          ui.actionResponder(ActionEvent.ERR_DUPLICATE_PHONE);
        } else {
          member.setPhoneNumber(phoneNumber);
        }
      }

      if (event == view.MainView.MenuEvent.Back) {
        running = false;
      }

    } while (running);
  }

  private void doItemMenu() {
    boolean running = true;

    do {
      view.MainView.MenuEvent event = ui.getItemMenuChoice();

      if (event == view.MainView.MenuEvent.AddItem) {
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

      if (event == view.MainView.MenuEvent.ListItems) {
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printItemList(items);
      }

      if (event == view.MainView.MenuEvent.DetailedItem) {
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

      if (event == view.MainView.MenuEvent.EditItem) {
        String id = ui.promptInformation(PromptEvent.ItemId);

        model.domain.Item.Mutable item = sls.findItemById(id);

        if (item != null) {
          doEditItemMenu(item);

        } else {
          ui.actionResponder(ActionEvent.ERR_FIND_ITEM);
        }
      }

      if (event == view.MainView.MenuEvent.LendItem) {
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
        int endDay = (startDayOfLoan - 1) + daysToLoan;

        model.domain.LendingContract contract = new LendingContract(lender, endDay, item, startDayOfLoan);
        boolean successfullyCreatedContract = sls.setUpLendingContract(contract);
        if (successfullyCreatedContract) {
          ui.actionResponder(ActionEvent.SUCCESS_CREATE_CONTRACT);
        } else {
          ui.actionResponder(ActionEvent.ERR_CREATE_CONTRACT);
        }
      } 

      if (event == view.MainView.MenuEvent.DeleteItem) {
        String itemId = ui.promptInformation(PromptEvent.ItemId);
        Boolean isSucceeded = sls.deleteItem(itemId);

        ui.actionResponder(isSucceeded ? ActionEvent.SUCCESS_DELETE : ActionEvent.ERR_DELETE);
      } 

      if (event == view.MainView.MenuEvent.Back) {
        running = false;
      } 
    } while (running);
  }

  private void doEditItemMenu(model.domain.Item.Mutable item) {
    boolean running = true;

    do {
      view.MainView.MenuEvent event = ui.getEditItemMenuChoice();

      if (event == view.MainView.MenuEvent.EditName) {
        String name = ui.promptInformation(PromptEvent.Name);

        item.setName(name);
      }
      
      if (event == view.MainView.MenuEvent.EditDescription) {
        String description = ui.promptInformation(PromptEvent.Description);

        item.setDescription(description);
      }

      if (event == view.MainView.MenuEvent.EditCost) {
        int cost = ui.promptInformationInt(PromptEvent.CostPerDay);

        item.setCostPerDay(cost);
      }

      if (event == view.MainView.MenuEvent.Back) {
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


