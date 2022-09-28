package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * Interface setting the contract for an ItemCollection.
 *
 */
public interface ItemCollection {
  public void addItem(Item item);

  public void removeItem(String itemId);

  public ArrayList<Item> getAllItems();

  public Item findItemById(String id);

  public boolean isUniqueItemId(String id);
  
  public ItemIterator typeIterator(ItemType type);
}
