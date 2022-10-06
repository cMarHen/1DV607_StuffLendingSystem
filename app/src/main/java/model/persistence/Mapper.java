package model.persistence;

import java.util.ArrayList;

/**
 * Interface for DataMapper.
 */
public interface Mapper<T> {
  T getById(String id);

  ArrayList<T> getAll();

  void saveAll(ArrayList<T> resources);

  Boolean delete(String id);
}
