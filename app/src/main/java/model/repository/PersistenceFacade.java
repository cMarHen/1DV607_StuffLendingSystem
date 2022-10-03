package model.repository;

import java.util.ArrayList;

import model.domain.Item;
import model.domain.ItemCollection;
import model.domain.ItemCollectionImpl;
import model.domain.Member;
import model.domain.MemberCollection;

public class PersistenceFacade {
  MapperFactory mapperFactory;
  MemberCollection members;
  ItemCollection items;

  public PersistenceFacade() {
    this.mapperFactory = new MapperFactory();

    loadMembers();
    loadItems(members); 
    // loadContracts(members, items);
  }

  // Return MemberCollection, used in SLS
  public MemberCollection getMembers() {
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
    this.members = mapper.loadAllMembers();
  }

  // Inject with members to be used when assign owners.
  private void loadItems(MemberCollection members) {
    ItemMapper mapper = new ItemMapper(members);
    this.items = mapper.loadAllItems();
  }

}
