package model.domain;

import java.util.ArrayList;

import model.domain.Item.ItemType;

public class StuffLendingSystem {
  ArrayList<Member> members = new ArrayList<>();
  ItemCollection items = new ItemCollectionImpl();
  ContractCollection contracts = new ContractCollection();
  RandomString randomStringGenerator = new RandomString();

  /**
   * Constructor
   */
  public StuffLendingSystem() {
    Member m1 = new Member("Anders", "Jonsson", "ander@gotmail.", "09523588235", getNewUniqueMemberId(), 2);
    Member m2 = new Member("Test", "Testsson", "test@gotmail.", "09523588205", getNewUniqueMemberId(), 5);
    members.add(m1);
    members.add(m2);

    addNewItem(m1, ItemType.Tool, "kratta", "Rinsing leafs", 0, 20);
    addNewItem(m1, ItemType.Game, "Super Mario", "playing", 0, 50);
    addNewItem(m2, ItemType.Sport, "Arsenal jersey", "jersey size xl", 0, 80);
  }

  public boolean addNewMember(String firstName, String lastName, String email, String phoneNumber, int dayOfCreation) {
    if (!isUniqueEmailAndPhoneNumber(email, phoneNumber)) {
      return false;
    }
    
    String id = getNewUniqueMemberId();
    Member newMember = new Member(firstName, lastName, email, phoneNumber, id, dayOfCreation);
    members.add(newMember);

    return true;
  }

  public boolean setUpLendingContract (Member lender, int endDay, Item item, int currentDay) {
    LendingContract newContract = new LendingContract(lender, endDay, item, currentDay);
    boolean successfullyAddedContract = contracts.addContract(newContract);

    if (successfullyAddedContract) {
      int contractFee = contracts.getContractFee(newContract);
      newContract.getLender().removeCredits(contractFee);
      newContract.getItem().getOwner().addCredits(contractFee);
      // TODO: Fixa bugg i credits som inte dras.
      newContract.getItem().setReserved(true);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Tells member to create an item, then add the item to list of items.
   * 
   * @param member
   * @param type
   * @param name
   * @param description
   * @param dayOfCreation
   * @param costPerDay
   * @return
   */
  public boolean addNewItem(Member member, ItemType type, String name, String description, int dayOfCreation, int costPerDay) {
    String id = getNewUniqueItemId();
    Item newItem = new Item(member, type, name, description, id, dayOfCreation, costPerDay);
    member.addCredits(100);
    items.addItem(newItem);

    return true;
  }

  public boolean deleteMember(String id) {
    Member member = findMemberById(id);

    if (member != null) {
      members.remove(member);
      return true;
    } else {
      return false;
    }
  }

  public Item findItemById(String id) {
    Item item = items.findItemById(id);

    return item;
  }

  public Member findMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        // TODO: Return DTO Object
        return member;
      }
    }
    return null;
  }

  public ArrayList<Member> getMembers() {
    // TODO: DEEP COPY!!
    return members;
  }

  public ArrayList<Item> getAllItems() {
    // TODO: DEEP COPY!!
    return items.getAllItems();
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
