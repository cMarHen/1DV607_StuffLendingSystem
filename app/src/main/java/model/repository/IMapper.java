package model.repository;

public interface IMapper {
  public Object get(ObjectIdentifier oid);
  public void put(ObjectIdentifier oid, Object obj);
}
