package model.domain;

public class Member {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String id;
  private int registredDay;

  public Member(String firstName, String lastName, String email, String phoneNumber, String id, int registredDay) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.registredDay = registredDay;
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

  public String getId() {
    return id;
  }

  public int getRegistredDay() {
    return registredDay;
  }
}
