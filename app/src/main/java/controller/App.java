package controller;

import model.domain.StuffLendingSystem;
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
      view.Console ui = new Console();
      model.domain.StuffLendingSystem sls = new StuffLendingSystem();
      MainController mainController = new MainController(ui, sls);

      mainController.doMainMenu();

      // sls.save();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
