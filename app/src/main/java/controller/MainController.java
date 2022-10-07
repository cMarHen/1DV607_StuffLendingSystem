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
  private view.Console ui;
  private model.domain.StuffLendingSystem sls;

  /**
   * Instaciate the MainController with a user interface representing the view and the main model class.
   *
   * @param ui - The view user interface. 
   * @param sls - The main class in the model.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "ui and sls should be a reference.")
  public MainController(view.Console ui, model.domain.StuffLendingSystem sls) {
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
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void doMemberMenu() {
    boolean running = true;

    do {
      view.Console.MemberEvent event = ui.getMemberMenuChoice();

      if (event == view.Console.MemberEvent.Back) {
        return;
      }
      
      if (event == view.Console.MemberEvent.AddMember) {
        boolean addMemberRunning = true;

        do {
          model.domain.Member newMember = ui.promptForNewMember();
  
          boolean isUniqueEmail = sls.isUniqueEmail(newMember.getEmail());
  
          if (!isUniqueEmail) {
            ui.printer.printDuplicateEmail();
            break;
          }
  
          boolean isUniquePhoneNumber = sls.isUniquePhoneNumber(newMember.getPhoneNumber());
  
          if (!isUniquePhoneNumber) {
            ui.printer.printDuplicatePhone();
            break;
          }
  
          boolean isSucceeded = sls.addNewMember(newMember);
  
          if (isSucceeded) {
            ui.printer.printCreateMemberSuccess();
          } else {
            ui.printer.printCreateMemberError();
          }


          addMemberRunning = false;
        } while (addMemberRunning);
      }

      if (event == view.Console.MemberEvent.ListMember) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printer.printMemberList(members, items);
      }

      if (event == view.Console.MemberEvent.ListMemberVerbose) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();

        for (Member.Mutable m : members) {
          Iterable<Item.Mutable> memberItems = sls.getItemByOwner(m);

          ui.printer.printVerboseMember(m);
          
          for (Item.Mutable item : memberItems) {
            ArrayList<model.domain.LendingContract> expiredContracts = sls.getExpiredContractsByItem(item);
            ArrayList<model.domain.LendingContract> activeContracts = sls.getContractsByItem(item);
            ui.printer.printDetailedItem(item, activeContracts, expiredContracts);
          }
        }
      }
      
      if (event == view.Console.MemberEvent.DetailedMember) {
        String id = ui.promptForMemberId();
        
        model.domain.Member member = sls.findMemberById(id);
        
        if (member != null) {
          ui.printer.printDetailedMember(member);
        } else {
          ui.printer.printFindMemberError();
        }
      }
      
      if (event == view.Console.MemberEvent.EditMember) {
        String id = ui.promptForMemberId();

        model.domain.Member.Mutable member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.printer.printFindMemberError();
        }
        
      }
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        String id = ui.promptForMemberId();

        boolean isSucceeded = sls.deleteMember(id);

        if (isSucceeded) {
          ui.printer.printDeleteMemberSuccess();
        } else {
          ui.printer.printDeleteMemberError();
        }
      }

    } while (running);
  }

  private void doEditMemberMenu(model.domain.Member.Mutable member) {
    boolean running = true;

    do {
      ui.printer.printDetailedMember(member);
      view.Console.MemberEditEvent event = ui.getEditMemberMenuChoice();

      if (event == view.Console.MemberEditEvent.EditFirstName) {
        String firstname = ui.promptForFirstName();

        member.setFirstName(firstname);
      }

      if (event == view.Console.MemberEditEvent.EditLastName) {
        String lastName =  ui.promptForLastName();

        member.setLastName(lastName);
      }

      if (event == view.Console.MemberEditEvent.EditEmail) {
        String email =  ui.promptForEmail();
        boolean isUniqueEmail = sls.isUniqueEmail(email);

        if (!isUniqueEmail) {
          ui.printer.printDuplicateEmail();
        } else {
          member.setEmail(email);
        }
      }

      if (event == view.Console.MemberEditEvent.EditPhone) {
        String phoneNumber =  ui.promptForPhone();
        boolean isUniquePhone = sls.isUniquePhoneNumber(phoneNumber);

        if (!isUniquePhone) {
          ui.printer.printDuplicatePhone();
        } else {
          member.setPhoneNumber(phoneNumber);
        }
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
        String memberId =  ui.promptForMemberId();
        model.domain.Member.Mutable member = sls.findMemberById(memberId);

        if (member != null) {
          model.domain.Item item = ui.promptForNewItem();
          sls.addNewItem(member, item);
          ui.printer.printCreateItemSuccess();
        } else {
          ui.printer.printFindMemberError();
        }

      } 

      if (event == view.Console.ItemEvent.ListItems) {
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printer.printItemList(items);
      }

      if (event == view.Console.ItemEvent.DetailedItem) {
        String id =  ui.promptForItemId();

        model.domain.Item item = sls.findItemById(id);

        if (item != null) {
          ArrayList<model.domain.LendingContract> expiredContracts = sls.getExpiredContractsByItem(item);
          ArrayList<model.domain.LendingContract> activeContracts = sls.getContractsByItem(item);
          ui.printer.printDetailedItem(item, activeContracts, expiredContracts);
        } else {
          ui.printer.printFindItemError();
        }
      } 

      if (event == view.Console.ItemEvent.EditItem) {
        String id = ui.promptForItemId();

        model.domain.Item.Mutable item = sls.findItemById(id);

        if (item != null) {
          doEditItemMenu(item);

        } else {
          ui.printer.printFindItemError();
        }
      }

      if (event == view.Console.ItemEvent.LendItem) {
        String itemId = ui.promptForItemId();
        model.domain.Item item = sls.findItemById(itemId);

        if (item == null) {
          ui.printer.printFindItemError();
          return;
        }

        String lenderId = ui.promptForMemberId();
        model.domain.Member lender = sls.findMemberById(lenderId);

        if (lender == null) {
          ui.printer.printFindMemberError();
          return;
        }

        int currentDay = sls.getCurrentDay();
        ui.printer.printCurrentDay(sls.getCurrentDay());

        int startDayOfLoan = 0;
        do {
          startDayOfLoan = ui.promptForLoanStartDay(); 
        } while (startDayOfLoan < currentDay);

        int daysToLoan = ui.promptForDaysToLoan();
        int endDay = (startDayOfLoan - 1) + daysToLoan;

        model.domain.LendingContract contract = new LendingContract(lender, endDay, item, startDayOfLoan);
        boolean successfullyCreatedContract = sls.setUpLendingContract(contract);
        if (successfullyCreatedContract) {
          ui.printer.printCreateContractSuccess();
        } else {
          ui.printer.printCreateContractError();
        }
      } 

      if (event == view.Console.ItemEvent.DeleteItem) {
        String itemId = ui.promptForItemId();
        Boolean isSucceeded = sls.deleteItem(itemId);

        if (isSucceeded) {
          ui.printer.printDeleteItemSuccess();
        } else {
          ui.printer.printDeleteItemError();
        }

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
        String name = ui.promptForItemName();

        item.setName(name);
      }
      
      if (event == view.Console.ItemEditEvent.EditDescription) {
        String description = ui.promptForItemDescription();

        item.setDescription(description);
      }

      if (event == view.Console.ItemEditEvent.EditCost) {
        int cost = ui.promptForCostPerDay();

        item.setCostPerDay(cost);
      }

      if (event == view.Console.ItemEditEvent.Back) {
        running = false;
      }

    } while (running);
  }
  
  private void doForwardDayMenu() {
    int amountOfDaysToProceed = ui.promptForDaysToProceed();
    
    for (int i = 0; i < amountOfDaysToProceed; i++) {
      sls.incrementCurrentDay();
    }

    ui.printer.printCurrentDay(sls.getCurrentDay());
  }
}


