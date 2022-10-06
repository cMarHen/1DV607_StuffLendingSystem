package model.persistence;

import java.util.ArrayList;

/**
 * Interface for StorageConverter.
 */
public interface StorageConverter<T> {
  public T get(String id);

  public ArrayList<T> getAll();

  public void put(ArrayList<T> resources);
}
