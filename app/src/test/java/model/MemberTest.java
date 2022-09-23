package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.domain.Member;

public class MemberTest {

  @Test
  public void testMemberCreated() {
    Member newMember = new Member("t", "t", "t", "t", "t", 0);

    assertTrue(newMember != null);
  }
}
