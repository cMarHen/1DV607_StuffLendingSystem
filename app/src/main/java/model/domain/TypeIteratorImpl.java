package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * Concrete class that implements the GOF Iterator-pattern.
 * The class iterates to search for item types.
 *
 */
public class TypeIteratorImpl implements ItemIterator {
  private ItemType type;
  private ArrayList<Item.Mutable> items;
  private int position;

  /**
   * Instanciate the iterator with type to search for and the list to implement the query on.
   *
   * @param type - ItemType to query for. 
   * @param items - A list of items matching the type.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference") // TODO:
  public TypeIteratorImpl(ItemType type, ArrayList<Item.Mutable> items) {
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
  public Item.Mutable next() {
    Item.Mutable item = items.get(position);
    position++;
    return item;
  }
  
}
