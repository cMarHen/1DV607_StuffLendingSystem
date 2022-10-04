package model.repository;

import java.util.ArrayList;
import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.Member;
import model.repository.mock.Mock;
import model.repository.mock.MockCollection;

/**
 * Class ItemMapper, used for mapping items from DB.
 * RDBMapper : Item
 */
public class ItemMapper extends PersistenceMapper {
  private MockCollection mocks;
  private ArrayList<Member.Mutable> members;

  public ItemMapper(ArrayList<Member.Mutable> members) {
    this.mocks = new MockCollection();
    this.members = members;
  }


  @Override
  protected Item.Mutable getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    Mock item = mocks.searchMockByOid(key);

    Member.Mutable m = findMember(item.getColumn("OWNER_ID")); 

    Item.Mutable newItem = new Item.Mutable(
        m,
        ItemType.valueOf(item.getColumn("TYPE")),
        item.getColumn("NAME"),
        item.getColumn("DESCRIPTION"),
        item.getColumn("ALPHA_ID"),
        Integer.parseInt(item.getColumn("CREATION_DAY")),
        Integer.parseInt(item.getColumn("COST")),
        item.getColumn("IS_RESERVED").equals("true")
        );
    return newItem;
  }

  /**
   * Load all items.
   *
   * @return - ItemCollection with items from database.
   */
  public ArrayList<Item.Mutable> loadAll() {
    ArrayList<Item.Mutable> i = new ArrayList<>();

    i.add(getObjectFromStorage(new ObjectIdentifier("oid_232345")));
    i.add(getObjectFromStorage(new ObjectIdentifier("oid_233456")));

    return i;
  }

  private Member.Mutable findMember(String id) {
    for (Member.Mutable m : members) {
      if (m.getId().equals(id)) {
        return m;
      }
    }

    return null;
  }
}
