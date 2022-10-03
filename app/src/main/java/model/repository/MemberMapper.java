package model.repository;

import java.util.ArrayList;

import model.domain.Member;
import model.repository.mock.IMock;
import model.repository.mock.MockCollection;

// RDBMapper : Member
public class MemberMapper extends PersistenceMapper{
  private MockCollection mocks;

  public MemberMapper() {
    this.mocks = new MockCollection(); // TODO: Should this be in superclass?
  }


  @Override
  protected Member.Mutable getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    IMock m = mocks.searchMockByOid(key);
    
    Member.Mutable newMember = new Member.Mutable(
      m.getColumn("FIRST_NAME"),
      m.getColumn("LAST_NAME"),
      m.getColumn("EMAIL"),
      m.getColumn("PHONE_NUMBER"),
      m.getColumn("ALPHA_ID"),
      Integer.parseInt(m.getColumn("REGISTERED_DAY")),
      Integer.parseInt(m.getColumn("CREDITS"))
      );
    return newMember;
  }


  public ArrayList<Member.Mutable> loadAllMembers() {
    ArrayList<Member.Mutable> m = new ArrayList<>();

    m.add(getObjectFromStorage(new ObjectIdentifier("oid_12345")));
    m.add(getObjectFromStorage(new ObjectIdentifier("oid_23456")));

    return m;
  }
}
