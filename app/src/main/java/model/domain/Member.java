package model.domain;

/**
 * Class representing a member in the stufflending system.
 *
 */
public class Member {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String id;
  private int credits;
  private int registredDay;

  /**
   * Instanciate Member with the data presented in the parameters and set credits to 0.
   *
   * @param firstName - User first name, editable later.
   * @param lastName - Users last name, editable later.
   * @param email - Users email, editable later.
   * @param phoneNumber - Users phonenumber, editable later.
   * @param id - Used to present a member-identifyer in the ui(view), not editable later.
   * @param registredDay - Metadata when user was added to the stufflending system, not editable later.
   * @param credits - Initial amount of credits for member.
   */
  public Member(String firstName, String lastName, String email, String phoneNumber, String id, int registredDay, int credits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = 0;
    this.id = id;
    this.registredDay = registredDay;
    this.credits = credits;
  }

  /**
   * Get the members firstname.
   *
   * @return - The firstname of the member.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Used to update members firstname.
   *
   * @param firstName - The name to be set as firstname.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Get the members lastname.
   *
   * @return - The lastname of the member.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Used to update members lastname.
   *
   * @param lastName - The name to be set as lastname.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Get the members email.
   *
   * @return - The email of the member.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Used to update members email.
   *
   * @param email - The name to be set as email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Get the members phone number.
   *
   * @return - The phone number of the member.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Used to update members phone number.
   *
   * @param phoneNumber - The name to be set as phone number.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Get the members amount of credits.
   *
   * @return - The amount of credits of the member.
   */
  public int getCredits() {
    return credits;
  }

  /**
   * Add an amount to the users credits.
   *
   * @param credits - Amount to add to the users credits.
   */
  public void addCredits(int credits) {
    this.credits += credits;
  }

  /**
   * Reduce an amount to the users credits.
   *
   * @param credits - Amount to reduce the users credits with.
   */
  public void removeCredits(int credits) {
    this.credits -= credits;
  }

  /**
   * Get the members id.
   *
   * @return - The id of the member.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the members day of registration.
   *
   * @return - The day of registration of the member.
   */
  public int getRegistredDay() {
    return registredDay;
  }
}
