package model.domain;

import java.util.ArrayList;

import model.domain.Item.ItemType;

public class TypeIteratorImpl implements ItemIterator {
  private ItemType type;
  private ArrayList<Item> items;
  private int position;

  public TypeIteratorImpl (ItemType type, ArrayList<Item> items) {
    this.type = type;
    this.items = items;
    this.position = 0;
  }

  @Override
  public boolean hasNext() {
    while (position < items.size()) {
      Item item = items.get(position);

      if (item.getType().equals(type)) {
        return true;
      } else {
        position++;
      }
    }

    return false;
  }

  @Override
  public Item next() {
    Item item = items.get(position);
    position++;
    return item;
  }
  
}
