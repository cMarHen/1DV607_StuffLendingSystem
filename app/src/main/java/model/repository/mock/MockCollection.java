package model.repository.mock;

import java.util.ArrayList;

/**
 * Collection of mock entities to simulate a RDB.
 */
public class MockCollection {
  ArrayList<Mock> mocks;

  /**
   * Constructor for MockCollection.
   * Inject mocks to collection on initialazion.
   */
  public MockCollection() {
    this.mocks = new ArrayList<>();

    injectMocksToList();
  }

  private void injectMocksToList() {
    mocks.add(new MemberMockOne());
    mocks.add(new MemberMockTwo());
    mocks.add(new ItemMockOne());
    mocks.add(new ItemMockTwo());
  }

  /**
   * Search mock by oid.
   *
   * @param oid - String as identifier.
   * @return Mock representation.
   */
  public Mock searchMockByOid(String oid) {
    for (Mock m : mocks) {
      if (m.getColumn("OID").equals(oid)) {
        return m;
      }
    }
    return null;
  }

  public Mock getAllMemberMocks() {
    return null;
  }
}
