package model.repository;

/**
 * Interface for Mapper.
 */
public interface Mapper {

  public Object get(ObjectIdentifier oid);

  public void put(ObjectIdentifier oid, Object obj);
}
