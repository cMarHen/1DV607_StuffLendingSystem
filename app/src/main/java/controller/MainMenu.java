package controller;

public class MainMenu {
  public void doMainMenu(view.Console ui) {
    boolean running = true;
    while (running) {
      view.Console.Event event = ui.getEvent();

      if (event == view.Console.Event.Quit) {
        running = false;
      }
    }
  }
}
