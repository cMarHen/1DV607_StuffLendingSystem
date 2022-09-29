package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * Class to manage querying and actions on the list of items.
 *
 */
public class ItemCollectionImpl implements ItemCollection {
  private ArrayList<Item> items;

  /**
   * Set the items-list to empty arraylist when instanciated.
   *
   */
  public ItemCollectionImpl() {
    items = new ArrayList<>();
  }

  /**
   * Get the whole list of items in the collection.
   *
   * @return - The whole list of items in the collection.
   */
  public ArrayList<Item> getAllItems() {
    ArrayList<Item> itemlistCopy = new ArrayList<>();

    for (Item item : this.items) {
      Item itemCopy = new Item(
          item.getOwner(),
          item.getType(),
          item.getName(),
          item.getDescription(),
          item.getId(),
          item.getDayOfCreation(),
          item.getCostPerDay(),
          item.getIsReserved());

      itemlistCopy.add(itemCopy);
    }
    return itemlistCopy;
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
      items.add(item);
    }
  }

  @Override
  public void removeItemById(String itemId) {
    for (Item item : items) {
      if (item.getId().equals(itemId)) {
        items.remove(item);
        return;
      }
    }
  }

  @Override
  public ItemIterator typeIterator(ItemType type) {
    return new TypeIteratorImpl(type, items);
  }

  @Override
  public Item findItemById(String id) {
    for (Item item : items) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Item> getItemsByOwner(Member owner) {
    ItemIterator iterator = ownerIterator(owner);
    ArrayList<Item> items = new ArrayList<>();

    while (iterator.hasNext()) {
      items.add(iterator.next());
    }

    return items;
  }

  @Override
  public ItemIterator ownerIterator(Member owner) {
    return new OwnerIteratorImpl(owner, items);
  }

  


}
