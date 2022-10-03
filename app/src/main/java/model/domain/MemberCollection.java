package model.domain;

import java.util.ArrayList;

public class MemberCollection {
  private ArrayList<Member.Mutable> members = new ArrayList<>();
  
  /**
   * Instaciate a new Member with unique id and add to the members-list.
   * Fails if email OR phonenumber is not unique.
   *
   * @param m - Wrapper for the member-data to create a new member based on.
   */
  public void addMember(Member.Mutable m) {
    members.add(m);
  }

  public Iterable<Member.Mutable> getMembers() {
    return members;
  }

  /**
   * Queries the members-list for Mutable Member object with matching id and returns a copy.
   *
   * @param id - Id used to query the members-list.
   * @return - A mutable member-object or null if not found.
   */
  public Member.Mutable findMemberById(String id) {
    for (Member.Mutable member : members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

  public void removeMember(Member.Mutable member) {
    members.remove(member);
  }

  public boolean isUniqueEmailAndPhoneNumber(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        return false;
      }
    }
    return true;
  }

  public boolean isUniqueMemberId(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
}
