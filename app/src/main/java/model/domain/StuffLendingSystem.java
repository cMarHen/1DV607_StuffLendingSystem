package model.domain;

import java.util.ArrayList;
import model.persistence.PersistenceFacade;


/**
 * The main class in the system and the Facade for the model.
 * This class handles members, items and contracts in the Stufflending System.
 *
 */
public class StuffLendingSystem {
  PersistenceFacade persistence;
  MemberCollection members;
  ItemCollection items;
  ContractCollection contracts;
  private RandomString randomStringGenerator = new RandomString();
  private int currentDay;

  /**
   * Constructor for SuffLendingSystem.
   *
   */
  public StuffLendingSystem() {
    this.persistence = new PersistenceFacade();
    this.members = persistence.getMemberCollection();
    this.items = persistence.getItemCollection();
    this.contracts = persistence.getContractCollection();
    this.currentDay = 0;
  }

  public void saveData() {
    persistence.save();
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

  public Iterable<Member.Mutable> getMembers() {
    return members.getMembers();
  }

  /**
   * Instaciate a new Member with unique id and add to the members-list.
   * Fails if email OR phonenumber is not unique.
   *
   * @param m - Wrapper for the member-data to create a new member based on.
   * @return - A flag if member successfully was added to the stufflending system.
   */
  public boolean addNewMember(Member m) {
    if (!members.isUniqueEmail(m.getEmail())) {
      return false;
    }
    
    if (!members.isUniquePhone(m.getPhoneNumber())) {
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
    members.addMember(newMember);

    return true;
  }

  /**
   * The item is reserved, credits are transfered and the contract is added to the list of contracts.
   * (Can fail if item is reserved or if lender has insufficient credits.)
   *
   * @param contract - The contract to validate.
   * @return - A flag if the contract was successfully implemented.
   */
  public boolean setUpLendingContract(LendingContract contract) {
    boolean successfullyAddedContract = contracts.addContract(contract);

    if (successfullyAddedContract) {
      Member.Mutable contractedLender = members.findMemberById(contract.getLender().getId());
      Member.Mutable contractedOwner = members.findMemberById(contract.getItem().getOwner().getId());
      
      if (!contractedLender.equals(contractedOwner)) {
        int contractFee = contract.getTotalContractFee();
        contractedLender.removeCredits(contractFee);
        contractedOwner.addCredits(contractFee);
      }

      if (contract.getStartDay() <= currentDay) {
        Item.Mutable contractedItem = findItemById(contract.getItem().getId());
        contractedItem.setReserved(true);
      }

      return true;
    } else {
      return false;
    }
  }

  /**
   * Instaciate a new Item with unique id and add to the items-list.
   *
   * @param member - Mutable member to set as owner of item and to add credits to.
   * @param item - Wrapper object containing information to create new item in the system.
   */
  public void addNewItem(Member.Mutable member, Item item) {
    String id = getNewUniqueItemId();
    Item newItem = new Item(
        member,
        item.getType(),
        item.getName(),
        item.getDescription(),
        id,
        item.getDayOfCreation(),
        item.getCostPerDay());
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
    Member.Mutable member = members.findMemberById(id);
    ArrayList<Item.Mutable> itemsOwned = items.getItemsByOwner(member);
    Boolean ownerHasReservedItems = contracts.ownerIsInActiveContract(id);
    

    if (!ownerHasReservedItems) {
      for (Item.Mutable item : itemsOwned) {
        items.removeItem(item);
      }
      
      if (member != null) {
        members.removeMember(member);
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
    Item.Mutable item = findItemById(id);
    if ((item != null) && (!contracts.itemHasActiveContract(item.getId()))) {
      items.removeItem(item);
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

    return item;
  }

  /**
   * Queries the members-list for Mutable Member object with matching id and returns a copy.
   *
   * @param id - Id used to query the members-list.
   * @return - A mutable member-object or null if not found.
   */
  public Member.Mutable findMemberById(String id) {
    return members.findMemberById(id);
  }

  /**
   * Get an iterable list of mutable items in the stufflending system.
   *
   * @return - Iterable list of mutable items in the stufflending system.
   */
  public Iterable<Item.Mutable> getAllItems() {
    return items.getAllItems();
  }

  public Iterable<Item.Mutable> getItemByOwner(Member m) {
    return items.getItemsByOwner(m);
  }

  public ArrayList<LendingContract> getContractsByItem(Item item) {
    return contracts.getContractsByItem(item);
  }

  public ArrayList<LendingContract> getExpiredContractsByItem(Item item) {
    return contracts.getExpiredContractsByItem(item);
  }

  public boolean isUniqueEmail(String email) {
    return members.isUniqueEmail(email);
  }

  public boolean isUniquePhoneNumber(String phoneNumber) {
    return members.isUniquePhone(phoneNumber);
  }

  private String getNewUniqueMemberId() {
    int lengthOfId = 6;
    boolean unique = false;
    String id = "";

    while (!unique) {
      id = randomStringGenerator.getAlphanumeric(lengthOfId);
      unique = members.isUniqueMemberId(id);
    }
    return id;
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
