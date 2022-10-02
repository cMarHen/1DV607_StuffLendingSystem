package model.repository;

public abstract class PersistenceMapper implements IMapper {
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
