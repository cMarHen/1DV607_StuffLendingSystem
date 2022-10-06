package controller;
import java.util.ArrayList;

import model.domain.Member;
import model.domain.Member.Mutable;
import model.domain.StuffLendingSystem;
import model.persistence.MemberConverter;
import model.persistence.MemberMapper;
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
      MemberMapper m = new MemberMapper();
      Member.Mutable newMed = new Member.Mutable("null", "null", "null", "null", "null", 0, 3432);
      ArrayList<Member.Mutable> members = m.getAll();
      System.out.println(members.size());

      members.add(newMed);
      
      m.saveAll(members);
      ArrayList<Member.Mutable> mem = m.getAll();

      for (Member.Mutable i : mem) {
        System.out.println(i.getFirstName() + i.getLastName());
        System.out.println(i.getId());
      }

      view.Console ui = new Console();
      model.domain.StuffLendingSystem sls = new StuffLendingSystem();
      MainController mainController = new MainController(ui, sls);

      // mainController.doMainMenu();

      // sls.save();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
