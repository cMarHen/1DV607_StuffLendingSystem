package model.repository;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to return a reference") // TODO:
  public MemberCollection getMembers() {
    return members;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to return a reference") // TODO:
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
