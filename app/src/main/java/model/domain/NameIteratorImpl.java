package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Concrete class that implements the GOF Iterator-pattern.
 * The class iterates to search for names, or part of name.
 *
 */
public class NameIteratorImpl implements ItemIterator {
  private ArrayList<Item.Mutable> items;
  private String name;
  private int position;
  private boolean isQuery;

  /**
   * Constructor for NameIteratorImpl.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public NameIteratorImpl(String name, ArrayList<Item.Mutable> items) {
    this.items = items;
    this.isQuery = name.charAt(name.length() - 1) == '?';
    this.name = isQuery ? name.split("\\?")[0].toLowerCase() : name.toLowerCase();
    this.position = 0;
  }

  @Override
  public boolean hasNext() {
    
    while (position < items.size()) {
      Item.Mutable item = items.get(position);
      String itemName = item.getName().toLowerCase();

      if (itemName.equals(name)) {
        return true;
      } else {
        String itemNameShorted = itemName.length() > this.name.length() 
            ? itemName.substring(0, this.name.length()) 
            : "";

        if (isQuery && itemNameShorted.equals(this.name)) {
          return true;
        } else {
          position++;
        }
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
