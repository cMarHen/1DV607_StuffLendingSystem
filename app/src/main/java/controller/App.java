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
    try {
      PersistenceFacade p = new PersistenceFacade();
    
      // TODO: .Load
      view.Console ui = new Console();
      model.domain.StuffLendingSystem sls = new StuffLendingSystem(p);
      MainController mainController = new MainController(ui, sls);

      mainController.doMainMenu();

      // TODO: .SAVE
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
