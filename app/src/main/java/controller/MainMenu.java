package controller;

public class MainMenu {
  private view.Console ui;

  public MainMenu(view.Console ui) {
    this.ui = ui;
  }

  public void doMainMenu() {
    boolean running = true;
    do {
      view.Console.MainEvent event = ui.getMainMenuChoice();

      if (event == view.Console.MainEvent.Quit) {
        running = false;
      }

      if (event == view.Console.MainEvent.MemberMenu) {
        doMemberMenu();
      }
    } while (running);
  }

  private void doMemberMenu() {
    boolean running = true;

    do {
      view.Console.MemberEvent event = ui.getMemberMenuChoice();

      if (event == view.Console.MemberEvent.Back) {
        return;
      }
      
      if (event == view.Console.MemberEvent.AddMember) {
        System.out.println("LÄGG TILL MEDLEM");
        return;
      }
      
      if (event == view.Console.MemberEvent.ListMember) {
        System.out.println("LISTA MEDLEMMAR");
        return;
      }
      
      if (event == view.Console.MemberEvent.DeleteMember) {
        System.out.println("WARNING; DELETE MEMBER!");
        return;
      }

    } while (running);
  }
}
