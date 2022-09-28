package model.domain;

/**
 * Interface used to implement the GOF Iterator-pattern.
 * The pattern is then used in search-queries.
 *
 */
public interface ItemIterator {
  public boolean hasNext();

  public Item next();
}
