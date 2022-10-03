package model.repository;

import model.domain.ItemCollection;
import model.domain.MemberCollection;

/**
 * Facade for Persistence handling.
 */
public class PersistenceFacade {
  MapperFactory mapperFactory;
  MemberCollection members;
  ItemCollection items;

  /**
   * Constructor for PersistenceFacade.
   * Load members and items on init.
   */
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
