package model.repository;

import model.domain.Member;

// RDBMapper : Member
public class MemberMapper extends PersistenceMapper{
  @Override
  protected Object getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    


    Member newMember = new Member(null, null, null, null, null, 0, 0);
    return newMember;
  }

  private String getFromMock(String oid, String field) {
    return "";
  }

  /* private String mockXYZ321(String field) {
    
  } */
}
