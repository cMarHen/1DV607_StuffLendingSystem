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
  private view.View ui;
  private view.MainView mainView;
  private model.domain.StuffLendingSystem sls;

  /**
   * Instaciate the MainController with a user interface representing the view and the main model class.
   *
   * @param ui - The view user interface. 
   * @param sls - The main class in the model.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "ui and sls should be a reference.")
  public MainController(view.MainView ui, model.domain.StuffLendingSystem sls) {
    this.sls = sls;
    this.mainView = ui;

    // NOTE: (Hardcoding change of view here.)
    // this.ui = ui.unAuthView;
    this.ui = ui.authView;
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

        if (event == view.MainView.MenuEvent.Login) {
          doLogin();
        }

        if (event == view.MainView.MenuEvent.Register) {
          doRegisterMember();
        }

        if (event == view.MainView.MenuEvent.Logout) {
          doLogout();
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
        // TODO: Remove.
      }

      if (event == view.MainView.MenuEvent.ListMember) {
        Iterable<model.domain.Member.Mutable> members = sls.getMembers();
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printer.printMemberList(members, items);
      }

      if (event == view.MainView.MenuEvent.ListMemberVerbose) {
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
      
      if (event == view.MainView.MenuEvent.DetailedMember) {
        String id = ui.promptForMemberId();
        
        model.domain.Member member = sls.findMemberById(id);
        
        if (member != null) {
          ui.printer.printDetailedMember(member);
        } else {
          ui.printer.printFindMemberError();
        }
      }
      
      if (event == view.MainView.MenuEvent.EditMember) {
        String id = ui.promptForMemberId();

        model.domain.Member.Mutable member = sls.findMemberById(id);

        if (member != null) {
          doEditMemberMenu(member);

        } else {
          ui.printer.printFindMemberError();
        }
        
      }
      
      if (event == view.MainView.MenuEvent.DeleteMember) {
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
      view.MainView.MenuEvent event = ui.getEditMemberMenuChoice();

      if (event == view.MainView.MenuEvent.EditFirstName) {
        String firstName =  ui.promptForFirstName();

        member.setFirstName(firstName);
      }

      if (event == view.MainView.MenuEvent.EditLastName) {
        String lastName =  ui.promptForLastName();

        member.setLastName(lastName);
      }

      if (event == view.MainView.MenuEvent.EditEmail) {
        String email =  ui.promptForEmail();
        boolean isUniqueEmail = sls.isUniqueEmail(email);

        if (!isUniqueEmail) {
          ui.printer.printDuplicateEmail();
        } else {
          member.setEmail(email);
        }
      }

      if (event == view.MainView.MenuEvent.EditPhone) {
        String phoneNumber =  ui.promptForPhone();
        boolean isUniquePhone = sls.isUniquePhoneNumber(phoneNumber);

        if (!isUniquePhone) {
          ui.printer.printDuplicatePhone();
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

      if (event == view.MainView.MenuEvent.ListItems) {
        Iterable<model.domain.Item.Mutable> items = sls.getAllItems();

        ui.printer.printItemList(items);
      }

      if (event == view.MainView.MenuEvent.DetailedItem) {
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

      if (event == view.MainView.MenuEvent.EditItem) {
        String id = ui.promptForItemId();

        model.domain.Item.Mutable item = sls.findItemById(id);

        if (item != null) {
          doEditItemMenu(item);

        } else {
          ui.printer.printFindItemError();
        }
      }

      if (event == view.MainView.MenuEvent.LendItem) {
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

      if (event == view.MainView.MenuEvent.DeleteItem) {
        String itemId = ui.promptForItemId();
        Boolean isSucceeded = sls.deleteItem(itemId);

        if (isSucceeded) {
          ui.printer.printDeleteItemSuccess();
        } else {
          ui.printer.printDeleteItemError();
        }
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
        String name = ui.promptForItemName();

        item.setName(name);
      }
      
      if (event == view.MainView.MenuEvent.EditDescription) {
        String description = ui.promptForItemDescription();

        item.setDescription(description);
      }

      if (event == view.MainView.MenuEvent.EditCost) {
        int cost = ui.promptForCostPerDay();

        item.setCostPerDay(cost);
      }

      if (event == view.MainView.MenuEvent.Back) {
        running = false;
      }

    } while (running);
  }

  private void doRegisterMember() {
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

          Password password = doPromptForPassword();

          // TODO: Create Auth-object and save in authservice.

          boolean isSucceeded = sls.addNewMember(newMember);
  
          if (isSucceeded) {
            ui.printer.printCreateMemberSuccess();
          } else {
            ui.printer.printCreateMemberError();
          }
          
          addMemberRunning = false;
        } while (addMemberRunning);
  }

  private Password doPromptForPassword() {
    boolean validPassword = false;
    Password password = null;
    do {
      String enteredPassword = ui.promptForPassword();

      try {
        password = new Password(enteredPassword);
        validPassword = true;
      } catch (Exception e) {
        ui.printer.printInvalidPassword();
      }
    } while (!validPassword);

    return password;
  }

  private void doLogin() {
    // TODO: Add login logic.
  }

  private void doLogout() {
    setUiStrategy(mainView.unAuthView);
  }

  private void setUiStrategy(view.View strategy) {
    this.ui = strategy;
  }
  
  private void doForwardDayMenu() {
    int amountOfDaysToProceed = ui.promptForDaysToProceed();
    
    for (int i = 0; i < amountOfDaysToProceed; i++) {
      sls.incrementCurrentDay();
    }

    ui.printer.printCurrentDay(sls.getCurrentDay());
  }
}


