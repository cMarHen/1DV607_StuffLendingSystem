package model.domain.iterators;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item;

/**
 * To be used to nest a search with AND operator.
 */
public class ItemAndIteratorImpl implements ItemIterator {
  private ItemIterator aiterator;
  private ItemIterator biterator;
  ArrayList<Item.Mutable> items;
  ArrayList<Item.Mutable> aitems = new ArrayList<>();
  ArrayList<Item.Mutable> bitems = new ArrayList<>();
  private int position;
  
  /**
   * Constructor for ItemAndIteratorImpl.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public ItemAndIteratorImpl(ItemIterator a, ItemIterator b, ArrayList<Item.Mutable> items) {
    this.aiterator = a;
    this.biterator = b;
    this.items = items;
    this.position = 0;
    loadItemsFromIterators();
  }

  @Override
  public boolean hasNext() {
    while (position < items.size()) {
      Item.Mutable item = items.get(position);
      for (Item.Mutable aitem : aitems) {
        for (Item.Mutable bitem : bitems) {
          if (item.getId().equals(aitem.getId()) && item.getId().equals(bitem.getId())) {
            return true;
          }
        }
      }
      position++;
    }
    return false;
  }

  @Override
  public Item.Mutable next() {
    Item.Mutable item = items.get(position);
    position++;
    return item;
  }
  
  private void loadItemsFromIterators() {
    while (aiterator.hasNext()) {
      aitems.add(aiterator.next());
    }

    while (biterator.hasNext()) {
      bitems.add(biterator.next());
    }
  }
}
