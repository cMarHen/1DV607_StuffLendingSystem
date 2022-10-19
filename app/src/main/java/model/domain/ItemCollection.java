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

  public ArrayList<Item.Mutable> getItemsByName(String name);

  public ArrayList<Item.Mutable> getItemsByType(ItemType type);

  public ArrayList<Item.Mutable> getItemsByTypeAndOwner(ItemType type, Member owner);

  public ArrayList<Item.Mutable> getItemsByNameAndType(String name, ItemType type);
  
  public ItemIterator typeIterator(ItemType type);

  public ItemIterator ownerIterator(Member owner);

  public ItemIterator nameIterator(String name);

  public ItemIterator nestedLogicalAndIterator(ItemIterator a, ItemIterator b);
  
  public ItemIterator nestedLogicalOrIterator(ItemIterator a, ItemIterator b);
}
