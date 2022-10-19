package view;

import java.util.Scanner;

/**
 * Main class and the Facade for the view.
 * 
 */
public class MainView {
  private Scanner scan;
  public AuthView authView;
  public UnAuthView unAuthView;

  /**
   * Events emmited from menu-choices.
   *
   */
  public enum MenuEvent {
    MemberMenu,
    ItemMenu,
    ForwardDay,
    Register,
    Login,
    Logout,
    AddMember,
    ListMember,
    ListMemberVerbose,
    DetailedMember,
    EditMember,
    DeleteMember,
    EditFirstName,
    EditLastName,
    EditEmail,
    EditPhone,
    AddItem,
    ListItems,
    SearchItems,
    DetailedItem,
    EditItem,
    LendItem,
    DeleteItem,
    EditName,
    EditDescription,
    EditCost,
    Back,
    Quit
  }

  /**
   * Instaciate the class and the scanner used for inputs and outputs in the UI.
   *
   */
  public MainView() {
    this.scan = new Scanner(System.in, "UTF8");
    this.authView = new AuthView(this.scan);
    this.unAuthView = new UnAuthView(this.scan);
  }
}