package model.domain;

import java.util.ArrayList;

public class StuffLendingSystem {
  ArrayList<Member> members = new ArrayList<>();
  RandomString randomStringGenerator = new RandomString();

  public boolean addNewMember(String firstName, String lastName, String email, String phoneNumber, int dayOfCreation) {
    if (!isUniqueEmailAndPhoneNumber(email, phoneNumber)) {
      return false;
    }
    
    String id = getNewUniqueId();
    Member newMember = new Member(firstName, lastName, email, phoneNumber, id, dayOfCreation);
    members.add(newMember);
    members.add(new Member("Anders", "Jonsson", "ander@gotmail.", "09523588235", getNewUniqueId(), 2));
    members.add(new Member("Test", "Testsson", "test@gotmail.", "09523588205", getNewUniqueId(), 5));

    return true;
  }

  public ArrayList<Member> getMembers() {
    // TODO: DEEP COPY!!
    return members;
  }

  private boolean isUniqueEmailAndPhoneNumber(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        return false;
      }
    }
    return true;
  }

  private String getNewUniqueId() {
    int lengthOfId = 6;
    boolean unique = false;
    String id = "";

    while (!unique) {
      id = randomStringGenerator.getAlphanumeric(lengthOfId);
      unique = isUniqueId(id);
    }
    return id;
  }

  private boolean isUniqueId(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
}
