package controller;

import model.auth.AuthServiceImpl;
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
      MainView ui = new MainView();
      StuffLendingSystem sls = new StuffLendingSystem();
      AuthServiceImpl auth = new AuthServiceImpl();
      MainController mainController = new MainController(ui, sls, auth);

      mainController.doMainMenu();

      sls.saveData();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
