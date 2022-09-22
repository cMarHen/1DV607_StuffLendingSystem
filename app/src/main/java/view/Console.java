package view;

import java.util.Scanner;

public class Console {
  Scanner scan;

  public static enum Event {
    AddMember,
    ListMembers,
    Quit
  }

  public Console(Scanner scan) {
    this.scan = scan;
  }

  public Event getEvent() {
    System.out.println("Enter your choice: ");

    return Event.Quit;
  }
}
