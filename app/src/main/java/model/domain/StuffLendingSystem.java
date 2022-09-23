package model.domain;

import java.util.ArrayList;

import model.domain.Item.ItemType;

public class StuffLendingSystem {
  ArrayList<Member> members = new ArrayList<>();
  ArrayList<Item> items = new ArrayList<>();
  RandomString randomStringGenerator = new RandomString();

  /**
   * Constructor
   */
  public StuffLendingSystem() {
    Member m1 = new Member("Anders", "Jonsson", "ander@gotmail.", "09523588235", getNewUniqueMemberId(), 2);
    members.add(m1);
    members.add(new Member("Test", "Testsson", "test@gotmail.", "09523588205", getNewUniqueMemberId(), 5));

    addNewItem(m1, ItemType.Tool, "kratta", "Rinsing leafs", 1, 20);
    addNewItem(m1, ItemType.Game, "Super Mario", "playing", 0, 50);

    items.get(1).setReserved(true);
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
    Item newItem = member.addItem(type, name, description, id, dayOfCreation, costPerDay);
    member.addCredits(100);
    items.add(newItem);

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

  public Member findMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        // TODO: Return DTO Object
        return member;
      }
    }
    return null;
  }

  public Item findItemById(String id) {
    for (Item item : items) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public ArrayList<Member> getMembers() {
    // TODO: DEEP COPY!!
    return members;
  }

  public ArrayList<Item> getItems() {
    // TODO: DEEP COPY!!
    return items;
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
      unique = isUniqueItemId(id);
    }
    return id;
  }

  private boolean isUniqueItemId(String id) {
    for (Item item : items) {
      if (item.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
}
