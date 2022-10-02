package model.repository;

import java.util.ArrayList;

import model.domain.ItemCollection;
import model.domain.ItemCollectionImpl;
import model.domain.Member;

public class PersistenceFacade {
  MapperFactory mapperFactory;
  ArrayList<Member> members;
  ItemCollection items;

  public PersistenceFacade() {
    this.mapperFactory = new MapperFactory();
    
    this.members = new ArrayList<>();
    this.items = new ItemCollectionImpl();

    loadMembers();
    // loadItems();
    // loadContracts();
  }

  // Return MemberCollection, used in SLS
  public ArrayList<Member> getMembers() {
    return members;
  }

  // Return ItemCollection, used in SLS
  public ItemCollection getItems() {
    return items;
  }

  public Member getMemberFromDb (ObjectIdentifier oid) {
    MemberMapper map = new MemberMapper();
    Member m = map.getObjectFromStorage(oid);
    return m;
  }

  private void loadMembers() {
    MemberMapper mapper = new MemberMapper();
    ArrayList<Member> membersFromDb = mapper.loadAllMembers();

    for (Member m : membersFromDb) {
      members.add(m);
    }
  }


}
