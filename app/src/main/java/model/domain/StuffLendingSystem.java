package model.domain;

import java.util.ArrayList;
import model.domain.Item.ItemType;

/**
 * The main class in the system and the Facade for the model.
 * This class handles members, items and contracts in the Stufflending System.
 *
 */
public class StuffLendingSystem {
  ArrayList<Member> members = new ArrayList<>();
  ItemCollection items = new ItemCollectionImpl();
  ContractCollection contracts = new ContractCollection();
  RandomString randomStringGenerator = new RandomString();
  private int currentDay;

  /**
   * TODO: Remove this.
   */
  public StuffLendingSystem() {
    this.currentDay = 0;

    Member m1 = new Member("Anders", "Jonsson", "ander@gotmail.", "09523588235", getNewUniqueMemberId(), 2);
    Member m2 = new Member("Test", "Testsson", "test@gotmail.", "09523588205", getNewUniqueMemberId(), 5);
    members.add(m1);
    members.add(m2);

    addNewItem(m1.getId(), ItemType.Tool, "kratta", "Rinsing leafs", 0, 20);
    addNewItem(m1.getId(), ItemType.Game, "Super Mario", "playing", 0, 50);
    addNewItem(m2.getId(), ItemType.Sport, "Arsenal jersey", "jersey size xl", 0, 80);

    ArrayList<Item> i = items.ownerIterator(m1);
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
      Item item = findItemById(contract.getItem().getId());
      item.setReserved(false);
    }

    for (LendingContract contract : activatedContracts) {
      Item item = findItemById(contract.getItem().getId());
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
   * @param firstName - The members first name, editable later.
   * @param lastName - The members last name, editable later.
   * @param email - The members email, editable later.
   * @param phoneNumber - The members phone number, editable later.
   * @return - A flag if member successfully was added to the stufflending system.
   */
  public boolean addNewMember(
      String firstName,
      String lastName,
      String email,
      String phoneNumber) {
    if (!isUniqueEmailAndPhoneNumber(email, phoneNumber)) {
      return false;
    }
    
    String id = getNewUniqueMemberId();
    Member newMember = new Member(firstName, lastName, email, phoneNumber, id, currentDay);
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
        contractedLender.removeCredits(contractFee);
        contractedOwner.addCredits(contractFee);
      }

      if (startDay <= currentDay) {
        item.setReserved(true);
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
   * @param memberId - Id of member to query for and then set as owner of item.
   * @param type - Used to specify item-type for searching, editable later.
   * @param name - Used to search for item by name, editable later.
   * @param description - Description of the item, editable later.
   * @param dayOfCreation - Metadata when item was created, not editable later.
   * @param costPerDay - Used in calculations to set up lending contracts, editable later.
   * @return - A flag indicated the item was successfully added to the item-list.
   */
  public boolean addNewItem(
        String memberId,
        ItemType type,
        String name,
        String description,
        int dayOfCreation,
        int costPerDay) {
    Member member = findOriginalMemberById(memberId);

    if (member != null) {
      String id = getNewUniqueItemId();
      Item newItem = new Item(member, type, name, description, id, dayOfCreation, costPerDay);
      member.addCredits(100);
      items.addItem(newItem);
  
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes member from the members-list.
   * Fails if no member with the id is found in the list.
   *
   * @param id - Id for querying the members-list.
   * @return - Flag if successfully removed member from the member-list.
   */
  public boolean deleteMember(String id) {
    // TODO: Get members items, is any item reserved abort, else delete items and member.
    Member member = findOriginalMemberById(id);

    if (member != null) {
      members.remove(member);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Queries the item-list for Item object with matching id.
   *
   * @param id - Id used to query the item-list.
   * @return - The Item-object or null if not found.
   */
  public Item findItemById(String id) {
    Item item = items.findItemById(id);

    // TODO: Copy the item here to not let it leave the SLS??????????
    return item;
  }

  /**
   * Queries the members-list for Member object with matching id and returns a copy.
   *
   * @param id - Id used to query the members-list.
   * @return - A copy of the member-object or null if not found.
   */
  public Member findMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        Member memberCopy = new Member(
            member.getFirstName(),
            member.getLastName(),
            member.getEmail(),
            member.getPhoneNumber(),
            id,
            member.getRegistredDay(),
            member.getCredits());

        return memberCopy;
      }
    }
    return null;
  }

  /**
   * Get a copy of the whole list of members in the stufflending system.
   *
   * @return - A copy of the list of members in the stufflending system.
   */
  public ArrayList<Member> getMembers() {
    ArrayList<Member> memberlistCopy = new ArrayList<>();

    for (Member member : this.members) {
      Member memberCopy = new Member(
          member.getFirstName(),
          member.getLastName(),
          member.getEmail(),
          member.getPhoneNumber(),
          member.getId(),
          member.getRegistredDay(),
          member.getCredits());

      memberlistCopy.add(memberCopy);
    }

    return memberlistCopy;
  }

  /**
   * Get the whole list of items in the stufflending system.
   *
   * @return - The list of items in the stufflending system.
   */
  public ArrayList<Item> getAllItems() {
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
