package controller;

import java.util.ArrayList;

import model.domain.Member;
import model.domain.StuffLendingSystem;
import model.repository.PersistenceFacade;
import view.Console;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    view.Console ui = new Console();
    model.domain.StuffLendingSystem sls = new StuffLendingSystem();
    MainController mainMenu = new MainController(ui, sls);

    PersistenceFacade f = new PersistenceFacade();
    ArrayList<Member> members = f.getMembers();

    System.out.println(members.get(0).getFirstName());
    System.out.println(members.get(1).getLastName());

    /* IMock m = new MockMemberOne();
    System.out.println(m.getColumn("FIRST_NAME")); */
    // mainMenu.doMainMenu();

  }
}
