package model.persistence;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.Member;
import model.persistence.mock.Mock;
import model.persistence.mock.MockCollection;

/**
 * Class ItemMapper, used for mapping items from DB.
 * RDBMapper : Item
 */
public class ItemMapper implements Mapper<Item.Mutable> {
  private MockCollection mocks;
  private ArrayList<Member.Mutable> members;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ItemMapper(ArrayList<Member.Mutable> members) {
    this.mocks = new MockCollection();
    this.members = members;
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
