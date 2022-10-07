package controller;

import model.domain.StuffLendingSystem;
import view.MainView;

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
      view.MainView ui = new MainView();
      model.domain.StuffLendingSystem sls = new StuffLendingSystem();
      MainController mainController = new MainController(ui, sls);

      mainController.doMainMenu();

      sls.saveData();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
