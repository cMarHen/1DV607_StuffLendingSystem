package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * Interface setting the contract for an ItemCollection.
 *
 */
public interface ItemCollection {
  public void addItem(Item item);

  public void removeItem(Item.Mutable item);

  public Iterable<Item.Mutable> getAllItems();

  public Item.Mutable findItemById(String id);
  
  public boolean isUniqueItemId(String id);
  
  public ArrayList<Item.Mutable> getItemsByOwner(Member owner);
  
  public ItemIterator typeIterator(ItemType type);

  public ItemIterator ownerIterator(Member owner);
}
