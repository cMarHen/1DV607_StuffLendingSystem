package controller;

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
    PersistenceFacade p = new PersistenceFacade();
    
    // TODO: .Load
    view.Console ui = new Console();
    model.domain.StuffLendingSystem sls = new StuffLendingSystem(p);
    MainController mainMenu = new MainController(ui, sls);

    mainMenu.doMainMenu();

    // TODO: .SAVE
  }
}
