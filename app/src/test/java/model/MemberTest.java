package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import model.domain.Member;
import org.junit.jupiter.api.Test;

/**
 * Test Member.
 */
public class MemberTest {
  Member newMember = new Member("t", "t", "t", "t", "t", 0);

  @Test
  public void createMember() {
    assertTrue(newMember != null);


  }

  @Test
  public void addAndRemoveCredits() {
    newMember.addCredits(150);
    assertTrue(newMember.getCredits() == 150);
    
    newMember.removeCredits(100);
    assertTrue(newMember.getCredits() == 50);
  }
}
