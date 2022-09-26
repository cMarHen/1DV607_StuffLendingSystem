package model.domain;

import java.util.ArrayList;

import model.domain.Item.ItemType;

public class ItemCollectionImpl implements ItemCollection {
  private ArrayList<Item> items;

  public ItemCollectionImpl() {
    items = new ArrayList<>();
  }

  public ArrayList<Item> getAllItems() {
    // TODO: DEEP COPY!!
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
      items.add(item);
    }
  }

  @Override
  public void removeItem(Item item) {
    if (item != null) {
      items.remove(item);
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
}
