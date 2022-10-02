package model.repository;

public class ObjectIdentifier {
  private String oid;
  public ObjectIdentifier(String oid) {
    this.oid = oid;
  }

  public String toString() {
    return oid;
  }
}
