package model.repository.mock;

public class MockMemberOne implements IMock {
  private String oid = "oid_12345"; // TODO: Add a real numeric
  private String firstName = "Martin";
  private String lastName = "Henriksson";
  private String email = "martin@mail.com";
  private String phoneNumber = "0123455";
  private String id = "Alphanum"; // TODO: Add a real numeric
  private String credits = "200";
  private String registredDay = "0";

  public MockMemberOne() {
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
