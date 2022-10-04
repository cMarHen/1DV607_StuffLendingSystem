package model.repository;

import java.util.ArrayList;


/**
 * Interface for Mapper.
 */
public interface Mapper {

  public Object get(ObjectIdentifier oid);

  public void put(ObjectIdentifier oid, Object obj);

  public ArrayList<? extends Object> loadAll();
}
