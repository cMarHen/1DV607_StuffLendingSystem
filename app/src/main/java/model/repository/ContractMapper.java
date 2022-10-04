package model.repository;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.Item;
import model.domain.LendingContract;
import model.domain.Member;
import model.repository.mock.Mock;
import model.repository.mock.MockCollection;

/**
 * Class ContractMapper, used for mapping contracts from DB.
 */
public class ContractMapper extends PersistenceMapper {
  private MockCollection mocks;
  private ArrayList<Member.Mutable> members;
  private ArrayList<Item.Mutable> items;

  /**
   * Init with members and items, to insert in LendingContract.
   *
   * @param m - List of members.
   * @param i - List of items.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ContractMapper(
      ArrayList<Member.Mutable> m,
      ArrayList<Item.Mutable> i
  ) {
    this.mocks = new MockCollection();
    this.members = m;
    this.items = i;
  }

  @Override
  protected LendingContract getObjectFromStorage(ObjectIdentifier oid) {
    String key = oid.toString();
    Mock contract = mocks.searchMockByOid(key);

    Member.Mutable m = findMember(contract.getColumn("LENDER_ID")); 
    Item.Mutable i = findItem(contract.getColumn("ITEM_ID"));

    LendingContract newContract = new LendingContract(
        m,
        Integer.parseInt(contract.getColumn("END_DAY")),
        i,
        Integer.parseInt(contract.getColumn("START_DAY"))
      );
    return newContract;
  }
  
  @Override
  public ArrayList<LendingContract> loadAll() {
    ArrayList<LendingContract> i = new ArrayList<>();

    i.add(getObjectFromStorage(new ObjectIdentifier("oid_264345")));

    return i;
  }

  private Member.Mutable findMember(String id) {
    for (Member.Mutable m : members) {
      if (m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

  private Item.Mutable findItem(String id) {
    for (Item.Mutable i : items) {
      if (i.getId().equals(id)) {
        return i;
      }
    }
    return null;
  }
  
}
