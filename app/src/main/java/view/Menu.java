package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;
import view.MainView.MenuEvent;

/**
 * Abstraction of a menu with the ability to prompt for int-choice.
 *
 */
public abstract class Menu {
  protected Scanner scan;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public Menu(Scanner scan) {
    this.scan = scan;
  }

  public abstract MenuEvent getMenuChoice();

  protected int promptForInt(String message) {
    int input = -1;
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextInt();
        scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        scan.nextLine();
        validInput = false;
      }
    } while (!validInput);

    return input;
  }
}
