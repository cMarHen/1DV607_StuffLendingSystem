package model.repository.mock;

import java.util.ArrayList;

public class MockCollection {
  ArrayList<IMock> mocks;

  public MockCollection () {
    this.mocks = new ArrayList<>();

    injectMocksToList();
  }

  private void injectMocksToList() {
    mocks.add(new MemberMockOne());
    mocks.add(new MemberMockTwo());
  }

  public IMock searchMockByOid(String oid) {
    for (IMock m : mocks) {
      if (m.getColumn("OID").equals(oid)) {
        return m;
      }
    }
    return null;
  }
}
