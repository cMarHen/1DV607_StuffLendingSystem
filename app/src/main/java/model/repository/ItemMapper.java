package model.repository;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.ItemCollection;
import model.domain.ItemCollectionImpl;
import model.domain.Member;
import model.domain.MemberCollection;
import model.repository.mock.Mock;
import model.repository.mock.MockCollection;

/**
 * Class ItemMapper, used for mapping items from DB.
 * RDBMapper : Item
 */
public class ItemMapper extends PersistenceMapper {
  private MockCollection mocks;
  private MemberCollection members;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ItemMapper(MemberCollection members) {
    this.mocks = new MockCollection(); // TODO: Should this be in superclass?
    this.members = members; // TODO: This is not good, strong coupling.
  }


  @Override
  protected Item getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    Mock item = mocks.searchMockByOid(key);
    // TODO: Hard coded an owner.
    Member.Mutable m = members.findMemberById(item.getColumn("OWNER_ID")); 

    Item newItem = new Item(
        m,
        ItemType.valueOf(item.getColumn("TYPE")),
        item.getColumn("NAME"),
        item.getColumn("DESCRIPTION"),
        item.getColumn("ALPHA_ID"),
        Integer.parseInt(item.getColumn("CREATION_DAY")),
        Integer.parseInt(item.getColumn("COST")),
        item.getColumn("IS_RESERVED").equals("true") // TODO: Is this correct?
        );
    return newItem;
  }

  /**
   * Load all items.
   *
   * @return - ItemCollection with items from database.
   */
  public ItemCollection loadAllItems() {
    ItemCollection i = new ItemCollectionImpl();

    i.addItem(getObjectFromStorage(new ObjectIdentifier("oid_232345")));
    i.addItem(getObjectFromStorage(new ObjectIdentifier("oid_233456")));

    return i;
  }
}
