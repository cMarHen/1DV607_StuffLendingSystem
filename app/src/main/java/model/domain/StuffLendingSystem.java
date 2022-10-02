package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * The main class in the system and the Facade for the model.
 * This class handles members, items and contracts in the Stufflending System.
 *
 */
public class StuffLendingSystem {
  ArrayList<Member.Mutable> members = new ArrayList<>();
  ItemCollection items = new ItemCollectionImpl();
  ContractCollection contracts = new ContractCollection();
  RandomString randomStringGenerator = new RandomString();
  private int currentDay;

  /**
   * TODO: Remove this.
   */
  public StuffLendingSystem() {
    this.currentDay = 0;

    Member.Mutable m1 = new Member.Mutable("Anders", "Jonsson", "ander@gotmail.", "09523588235", getNewUniqueMemberId(), 2);
    Member.Mutable m2 = new Member.Mutable("Test", "Testsson", "test@gotmail.", "09523588205", getNewUniqueMemberId(), 5);
    addNewMember(m1);
    addNewMember(m2);

    addNewItem(m1, new Item(ItemType.Tool, "kratta", "Rinsing leafs", 20));
    addNewItem(m1, new Item(ItemType.Game, "Super Mario", "playing", 50));
    addNewItem(m2, new Item(ItemType.Sport, "Arsenal jersey", "jersey size xl", 80));

    
    ItemIterator iterator = items.ownerIterator(m1);
    ArrayList<Item> i = new ArrayList<>();

    while (iterator.hasNext()) {
      i.add(iterator.next());
    }

    for (Item item : i) {
      System.out.println(item.getName());
    }
    
  }

  /**
   * Increment the current day.
   * Cleans up expired contracts, finding newly activated contracts and updating the items-stats.
   */
  public void incrementCurrentDay() {
    currentDay++;
    ArrayList<LendingContract> expiredContracts = contracts.cleanExpiredContracts(currentDay);
    ArrayList<LendingContract> activatedContracts = contracts.getActivatedContracts(currentDay);

    for (LendingContract contract : expiredContracts) {
      Item.Mutable item = findItemById(contract.getItem().getId());
      item.setReserved(false);
    }

    for (LendingContract contract : activatedContracts) {
      Item.Mutable item = findItemById(contract.getItem().getId());
      item.setReserved(true);
    }
  }

  public int getCurrentDay() {
    return currentDay;
  }

  /**
   * Instaciate a new Member with unique id and add to the members-list.
   * Fails if email OR phonenumber is not unique.
   *
   * @param m - Wrapper for the member-data to create a new member based on.
   * @return - A flag if member successfully was added to the stufflending system.
   */
  public boolean addNewMember(Member m) {
    if (!isUniqueEmailAndPhoneNumber(m.getEmail(), m.getPhoneNumber())) {
      return false;
    }
    
    String id = getNewUniqueMemberId();
    Member.Mutable newMember = new Member.Mutable(
        m.getFirstName(),
        m.getLastName(),
        m.getEmail(),
        m.getPhoneNumber(),
        id,
        currentDay);
    members.add(newMember);

    return true;
  }

  /**
   * The item is reserved, credits are transfered and the contract is added to the list of contracts.
   * (Can fail if item is reserved or if lender has insufficient credits.)
   *
   * @param lenderId - Id of the member whom to pay credits for the total lending-fee.
   * @param endDay - The day after the endDay the item is available for loan again.
   * @param itemId - The id of the item that holds information about the owner to recieve credits for the loan.
   * @return - A flag if the contract was successfully implemented.
   */
  public boolean setUpLendingContract(String lenderId, int startDay, int endDay, String itemId) {
    Member lender = findOriginalMemberById(lenderId);
    Item item = findItemById(itemId);
    LendingContract newContract = new LendingContract(lender, endDay, item, startDay);
    boolean successfullyAddedContract = contracts.addContract(newContract);

    if (successfullyAddedContract) {
      Member contractedLender = findOriginalMemberById(newContract.getLender().getId());
      Member contractedOwner = findOriginalMemberById(newContract.getItem().getOwner().getId());
      
      // TODO: Should money be taken on booking, or when the loan starts?
      if (!contractedLender.equals(contractedOwner)) {
        int contractFee = newContract.getTotalContractFee();
        // contractedLender.removeCredits(contractFee);
        // contractedOwner.addCredits(contractFee);
      }

      if (startDay <= currentDay) {
        // item.setReserved(true);
      }

      return true;
    } else {
      return false;
    }
  }

  /**
   * Instaciate a new Item with unique id and add to the items-list.
   * Fails if no member is found in the stufflending system.
   *
   * @param member - Mutable member to set as owner of item and to add credits to.
   * @param item - Wrapper object containing information to create new item in the system.
   */
  public void addNewItem(Member.Mutable member, Item item) {
    String id = getNewUniqueItemId();
    Item.Mutable newItem = new Item.Mutable(member, item.getType(), item.getName(), item.getDescription(), id, item.getDayOfCreation(), item.getCostPerDay());
    member.addCredits(100);
    items.addItem(newItem);
  }

  /**
   * Removes member from the members-list.
   * Fails if no member with the id is found in the list.
   *
   * @param id - Id for querying the members-list.
   * @return - Flag if successfully removed member from the member-list.
   */
  public boolean deleteMember(String id) {
    Member member = findOriginalMemberById(id);
    ArrayList<Item> itemsOwned = items.getItemsByOwner(member);
    Boolean ownerHasReservedItems = contracts.ownerIsInActiveContract(id);
    

    if (!ownerHasReservedItems) {
      for (Item item : itemsOwned) {
        items.removeItemById(item.getId());
      }
      
      if (member != null) {
        members.remove(member);
        return true;
      } 
    }

    return false;
  }

  /**
   * Removes item from the item-list.
   * Fails if no member with the id is found in the list.
   * 
   * @param id - Id for querying the item-list.
   * @return - Flag if successfully removed item from the item-list.
   */
  public boolean deleteItem(String id) {
    Item item = findItemById(id);
    if ((item != null) && (!contracts.itemHasActiveContract(item.getId()))) {
      items.removeItemById(item.getId());
      return true;
    }

    return false;
  }

  /**
   * Queries the item-list for Item object with matching id.
   *
   * @param id - Id used to query the item-list.
   * @return - The Item-object or null if not found.
   */
  public Item.Mutable findItemById(String id) {
    Item.Mutable item = items.findItemById(id);

    // TODO: Copy the item here to not let it leave the SLS??????????
    return item;
  }

  /**
   * Queries the members-list for Mutable Member object with matching id and returns a copy.
   *
   * @param id - Id used to query the members-list.
   * @return - A mutable member-object or null if not found.
   */
  public Member.Mutable findMemberById(String id) {
    for (Member.Mutable member : members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Get an iterable list of members in the stufflending system.
   *
   * @return - A iterable list of members in the stufflending system.
   */
  public Iterable<Member.Mutable> getMembers() {
    return members;
  }

  /**
   * Get an iterable list of mutable items in the stufflending system.
   *
   * @return - Iterable list of mutable items in the stufflending system.
   */
  public Iterable<Item.Mutable> getAllItems() {
    return items.getAllItems();
  }

  private Member findOriginalMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

  private boolean isUniqueEmailAndPhoneNumber(String email, String phoneNumber) {
    for (Member member : members) {
      if (member.getEmail().equals(email) || member.getPhoneNumber().equals(phoneNumber)) {
        return false;
      }
    }
    return true;
  }

  private String getNewUniqueMemberId() {
    int lengthOfId = 6;
    boolean unique = false;
    String id = "";

    while (!unique) {
      id = randomStringGenerator.getAlphanumeric(lengthOfId);
      unique = isUniqueMemberId(id);
    }
    return id;
  }

  private boolean isUniqueMemberId(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }

  private String getNewUniqueItemId() {
    int lengthOfId = 6;
    boolean unique = false;
    String id = "";
    String idPrefix = "item_";

    while (!unique) {
      id = idPrefix + randomStringGenerator.getAlphanumeric(lengthOfId);
      unique = items.isUniqueItemId(id);
    }
    return id;
  }
}
