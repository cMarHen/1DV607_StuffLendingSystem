package model.repository;

/**
 * Abstract class PersistenceMapper.
 */
public abstract class PersistenceMapper implements Mapper {
  @Override
  public Object get(ObjectIdentifier oid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void put(ObjectIdentifier oid, Object obj) {
    // TODO Auto-generated method stub
    
  }

  protected Object getObjectFromStorage(ObjectIdentifier oid) {
    return null;
  }
  
}
