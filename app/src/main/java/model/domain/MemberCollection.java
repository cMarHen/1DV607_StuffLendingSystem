package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Wrapper-class to manage a collection of Members.
 *
 */
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

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
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


  /**
   * Flag if email and phone number are unique.
   *
   * @param email - The email to check.
   * @param phoneNumber - The phone number to check.
   * @return- Flag if any is not unique.
   */
  public boolean isUniqueEmailAndPhoneNumber(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Flag if memberId is unique.
   *
   * @param id - The id to check.
   * @return- Flag if memberId is not unique.
   */
  public boolean isUniqueMemberId(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
}
