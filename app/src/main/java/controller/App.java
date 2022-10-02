package controller;

import model.domain.StuffLendingSystem;
import model.repository.mock.IMock;
import model.repository.mock.MockMemberOne;
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

    IMock m = new MockMemberOne();
    System.out.println(m.getColumn("FIRST_NAME"));
    // mainMenu.doMainMenu();

  }
}
