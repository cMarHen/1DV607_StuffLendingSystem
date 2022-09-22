package model.domain;

public class StuffLendingSystem {
  public boolean addNewMember(String firstName, String lastName, String email, String phoneNumber) {
    System.out.println("NYA MEDLEMMEN: ");
    System.out.println(firstName + " " + lastName);
    System.out.println(email);
    System.out.println(phoneNumber);

    return true;
  }
}
