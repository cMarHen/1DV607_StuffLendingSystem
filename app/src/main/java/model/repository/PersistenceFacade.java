package model.repository;

import java.util.ArrayList;
import model.domain.Item;
import model.domain.ItemCollection;
import model.domain.ItemCollectionImpl;
import model.domain.Member;
import model.domain.MemberCollection;

/**
 * Facade for Persistence handling.
 */
public class PersistenceFacade {
  MapperFactory mapperFactory;
  MemberCollection memberCollection;
  ItemCollection itemCollection;

  /**
   * Constructor for PersistenceFacade.
   * Load members and items on init.
   */
  public PersistenceFacade() {
    this.mapperFactory = new MapperFactory();
    this.memberCollection = new MemberCollection();
    this.itemCollection = new ItemCollectionImpl();
  }

  /**
   * Get MemberCollection, populated with members from storage.
   */
  public MemberCollection getMemberCollection() {
    ArrayList<Member.Mutable> members = mapperFactory.getMemberMapper().loadAll();

    for (Member.Mutable m : members) {
      memberCollection.addMember(m);
    }
    return memberCollection;
  }

  /**
   * Get ItemCollection, populated with items from storage.
   */
  public ItemCollection getItemCollection() {
    ArrayList<Item.Mutable> items = mapperFactory.getItemMapper().loadAll();
    
    for (Item.Mutable i : items) {
      itemCollection.addItem(i);
    }
    return itemCollection;
  }
}
