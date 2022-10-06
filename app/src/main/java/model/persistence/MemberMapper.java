package model.persistence;

import java.util.ArrayList;
import model.domain.Member;

/**
 * Class MemberMapper, used for mapping members from DB.
 */
public class MemberMapper implements Mapper<Member.Mutable> {
  private MemberConverter orm;

  public MemberMapper() {
    this.orm = new MemberConverter();
  }

  @Override
  public Member.Mutable getById(String id) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Member.Mutable> getAll() {
    return orm.getAll();
  }

  @Override
  public Boolean delete(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void saveAll(ArrayList<Member.Mutable> resources) {
    orm.put(resources);
  }
  
}
