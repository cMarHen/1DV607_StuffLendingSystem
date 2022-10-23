package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item.Mutable;
  
/**
 * Constructor for ItemOrIteratorImpl.
 */
public class ItemOrIteratorImpl implements ItemIterator {
  private ItemIterator aiterator;
  private ItemIterator biterator;
  ArrayList<Item.Mutable> items;
  ArrayList<Item.Mutable> aitems = new ArrayList<>();
  ArrayList<Item.Mutable> bitems = new ArrayList<>();
  private int position;

  /**
   * Constructor for ItemOrIteratorImpl.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public ItemOrIteratorImpl(ItemIterator a, ItemIterator b, ArrayList<Item.Mutable> items) {
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
          if (item.getId().equals(aitem.getId()) || item.getId().equals(bitem.getId())) {
            return true;
          }
        }
      }
      position++;
    }
    return false;
  }

  @Override
  public Mutable next() {
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
