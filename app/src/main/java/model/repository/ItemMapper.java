package model.repository;

import java.util.ArrayList;

import model.domain.Item;
import model.domain.Member;
import model.domain.Item.ItemType;
import model.repository.mock.IMock;
import model.repository.mock.MockCollection;

public class ItemMapper extends PersistenceMapper {
  private MockCollection mocks;
  private ArrayList<Member.Mutable> members;

  public ItemMapper(ArrayList<Member.Mutable> members) {
    this.mocks = new MockCollection(); // TODO: Should this be in superclass?
    this.members = members; // TODO: This is not good, strong coupling.
  }


  @Override
  protected Item getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    IMock i = mocks.searchMockByOid(key);
    // TODO: Hard coded an owner.
    Member.Mutable m = /* i.getColumn("OWNER_ID") */ members.get(0); 

    Item newItem = new Item(
      m,
      ItemType.valueOf(i.getColumn("TYPE")),
      i.getColumn("NAME"),
      i.getColumn("DESCRIPTION"),
      i.getColumn("ALPHA_ID"),
      Integer.parseInt(i.getColumn("CREATION_DAY")),
      Integer.parseInt(i.getColumn("COST")),
      i.getColumn("IS_RESERVED").equals("true") // TODO: Is this correct?
      );
    return newItem;
  }


  public ArrayList<Item> loadAllItems() {
    ArrayList<Item> i = new ArrayList<>();

    i.add(getObjectFromStorage(new ObjectIdentifier("oid_232345")));
    i.add(getObjectFromStorage(new ObjectIdentifier("oid_233456")));

    return i;
  }
}
