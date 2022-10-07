package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;
import model.domain.iterators.ItemIterator;

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

  public ArrayList<Item.Mutable> getItemsByName(String name);
  
  public ItemIterator typeIterator(ItemType type);

  public ItemIterator ownerIterator(Member owner);

  public ItemIterator nameIterator(String name);

  public ItemIterator nestedLogicalAndIterator(ItemIterator a, ItemIterator b);
  
  public ItemIterator nestedLogicalOrIterator(ItemIterator a, ItemIterator b);
}
