package model.repository.mock;

/**
 * Mock for Member nr 2. 
 */
public class MemberMockThree implements Mock {
  
  private String oid = "oid_78901";
  private String firstName = "Tobias";
  private String lastName = "Olsson";
  private String email = "hobbe@mail.com";
  private String phoneNumber = "55510067";
  private String id = "HyG1P3";
  private String credits = "0";
  private String registredDay = "0";

  public MemberMockThree() {
  }

  @Override
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
