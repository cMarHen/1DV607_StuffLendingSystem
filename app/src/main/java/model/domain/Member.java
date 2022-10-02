package model.domain;

/**
 * Class representing a member in the stufflending system.
 *
 */
public class Member {
  protected String firstName;
  protected String lastName;
  protected String email;
  protected String phoneNumber;
  private String id;
  protected int credits;
  private int registredDay;

  /**
   * Instanciate Member with the readonly Member-object.
   *
   * @param m - Readonly member-object.
   */
  public Member(Member m) {
    this.firstName = m.getFirstName();
    this.lastName = m.getLastName();
    this.email = m.getEmail();
    this.phoneNumber = m.getPhoneNumber();
    this.credits = m.getCredits();
    this.id = m.getId();
    this.registredDay = m.getRegistredDay();
  }

  /**
   * Instanciate Member with the data presented in the parameters and set credits to 0.
   *
   * @param firstName - User first name, editable later.
   * @param lastName - Users last name, editable later.
   * @param email - Users email, editable later.
   * @param phoneNumber - Users phonenumber, editable later.
   */
  public Member(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = 0;
  }

  /**
   * Instanciate Member with the data presented in the parameters and set credits to 0.
   *
   * @param firstName - User first name, editable later.
   * @param lastName - Users last name, editable later.
   * @param email - Users email, editable later.
   * @param phoneNumber - Users phonenumber, editable later.
   * @param id - Used to present a member-identifyer in the ui(view), not editable later.
   * @param registredDay - Metadata when user was added to the stufflending system, not editable later.
   */
  public Member(String firstName, String lastName, String email, String phoneNumber, String id, int registredDay) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.registredDay = registredDay;
    this.credits = 0;
  }

  /**
   * Instanciate Member with the data presented in the parameters including credits.
   *
   * @param firstName - User first name, editable later.
   * @param lastName - Users last name, editable later.
   * @param email - Users email, editable later.
   * @param phoneNumber - Users phonenumber, editable later.
   * @param id - Used to present a member-identifyer in the ui(view), not editable later.
   * @param registredDay - Metadata when user was added to the stufflending system, not editable later.
   * @param credits - Initial amount of credits for member.
   */
  public Member(
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String id,
      int registredDay,
      int credits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.registredDay = registredDay;
    this.credits = credits;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public int getCredits() {
    return credits;
  }

  public String getId() {
    return id;
  }

  public int getRegistredDay() {
    return registredDay;
  }

  public static class Mutable extends Member {

    public Mutable(Member m){
      super(m);
    }

    public Mutable(String firstName, String lastName, String email, String phoneNumber){
      super(firstName, lastName, email, phoneNumber);
    }

    public Mutable(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String id,
        int registredDay){
      super(firstName, lastName, email, phoneNumber, id, registredDay);
    }

    public Mutable(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String id,
        int registredDay,
        int credits){
      super(firstName, lastName, email, phoneNumber, id, registredDay, credits);
    }

    public void setFirstName(String firstname) {
      this.firstName = firstname;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public void addCredits(int credits) {
      this.credits += credits;
    }

    public void removeCredits(int credits) {
      this.credits -= credits;
    }
  }
}
