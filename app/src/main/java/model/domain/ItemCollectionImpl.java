package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item.ItemType;
import model.domain.iterators.ItemIterator;
import model.domain.iterators.NameIteratorImpl;
import model.domain.iterators.OwnerIteratorImpl;
import model.domain.iterators.TypeIteratorImpl;

/**
 * Class to manage querying and actions on the list of items.
 *
 */
public class ItemCollectionImpl implements ItemCollection {
  private ArrayList<Item.Mutable> items;

  /**
   * Set the items-list to empty arraylist when instanciated.
   *
   */
  public ItemCollectionImpl() {
    items = new ArrayList<>();
  }
  
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Item.Mutable> getAllItems() {
    return items;
  }

  @Override
  public boolean isUniqueItemId(String id) {
    for (Item item : items) {
      if (item.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public void addItem(Item item) {
    if (item != null) {
      Item.Mutable mutableItem = new Item.Mutable(item);
      items.add(mutableItem);
    }
  }

  @Override
  public void removeItem(Item.Mutable item) {
    items.remove(item);
  }

  @Override
  public ItemIterator typeIterator(ItemType type) {
    return new TypeIteratorImpl(type, items);
  }

  @Override
  public Item.Mutable findItemById(String id) {
    for (Item.Mutable item : items) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Item.Mutable> getItemsByOwner(Member owner) {
    ItemIterator iterator = ownerIterator(owner);
    ArrayList<Item.Mutable> items = new ArrayList<>();

    while (iterator.hasNext()) {
      items.add(iterator.next());
    }

    return items;
  }

  @Override
  public Item.Mutable getItemByName(String name) {
    ItemIterator iterator = nameIterator(name);
    Item.Mutable item;
    while (iterator.hasNext()) {
      item = iterator.next();
      return item;
    }
    return null;
  }
  

  @Override
  public ItemIterator ownerIterator(Member owner) {
    return new OwnerIteratorImpl(owner, items);
  }

  @Override
  public ItemIterator nameIterator(String name) {
    return new NameIteratorImpl(name, items);
  }

  @Override
  public ItemIterator nestedLogicalAndIterator(ItemIterator a, ItemIterator b) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ItemIterator nestedLogicalOrIterator(ItemIterator a, ItemIterator b) {
    // TODO Auto-generated method stub
    return null;
  }

  


}
