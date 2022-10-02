package model.repository.mock;

public class MemberMockTwo implements IMock {
  
  private String oid = "oid_23456"; // TODO: Add a real numeric
  private String firstName = "Anders";
  private String lastName = "Jonsson";
  private String email = "anders@mail.com";
  private String phoneNumber = "1234556";
  private String id = "Alphanumir"; // TODO: Add a real numeric
  private String credits = "200";
  private String registredDay = "0";

  public MemberMockTwo() {
  }

  public String getColumn(String column) {
    if (column.equals("OID")) {
      return this.oid;
    }  else if (column.equals("FIRST_NAME")) {
      return this.firstName;
    } else if (column.equals("LAST_NAME")) {
      return this.lastName;
    } else if (column.equals("EMAIL")) {
      return this.email;
    } else if (column.equals("PHONE_NUMBER")) {
      return this.phoneNumber;
    } else if (column.equals("ALPHA_ID")) {
      return this.id;
    } else if (column.equals("CREDITS")) {
      return this.credits;
    } else if (column.equals("REGISTERED_DAY")) {
      return this.registredDay;
    }

    return null;
  }
}
