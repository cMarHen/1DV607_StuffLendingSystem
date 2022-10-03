package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import model.domain.Item;
import model.domain.Item.ItemType;
import model.domain.LendingContract;
import model.domain.Member;
import org.junit.jupiter.api.Test;

/**
 * Test LendingContract.
 */
public class LendingContractTest {
  Member.Mutable newMember = new Member.Mutable("t", "t", "t", "t", "t", 0);
  Item item = new Item(newMember, ItemType.Game, "test", "test", "test_id", 0, 2);
  LendingContract contract = new LendingContract(newMember, 3, item, 0);

  @Test
  public void createNewContract() {
    assertTrue(contract != null);
  }

  @Test
  public void readLenderOfContract() {
    assertTrue(contract.getLender() != null);
    assertTrue(contract.getLender().getId().equals(newMember.getId()));
  }

  @Test
  public void readStartAndEndDayOfContract() {
    assertTrue(contract.getStartDay() == 0);
    assertTrue(contract.getEndDay() == 3);
  }
}
