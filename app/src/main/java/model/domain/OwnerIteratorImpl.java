package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Concrete class that implements the GOF Iterator-pattern.
 * The class iterates to search for item owner.
 *
 */
public class OwnerIteratorImpl implements ItemIterator {
  private ArrayList<Item.Mutable> items;
  private Member owner;
  private int position;

  /**
   * Constructor for OwnerIteratorImpl.
   *
   * @param owner - A member as owner.
   * @param items - The list of items to search.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference") // TODO:
  public OwnerIteratorImpl(Member owner, ArrayList<Item.Mutable> items) {
    this.items = items;
    this.owner = owner;
    this.position = 0;
  }

  @Override
  public boolean hasNext() {
    
    while (position < items.size()) {
      Item.Mutable item = items.get(position);
      
      if (item.getOwner().getId().equals(owner.getId())) {
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
