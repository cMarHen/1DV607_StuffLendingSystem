package model.domain;

/**
 * Class representing an item in the stufflending system.
 *
 */
public class Item {
  private Member owner;
  private boolean isReserved;
  private ItemType type;
  private String name;
  private String description;
  private String id;
  private int dayOfCreation;
  private int costPerDay;

  /**
   * Available item-types.
   *
   */
  public static enum ItemType {
    Tool,
    Vehicle,
    Game,
    Toy,
    Sport,
    Other
  }

  /**
   * Instanciate an item with the data presented as parameters, sets isReserved to false.
   *
   * @param owner - Used to identify the owner when/if credits are to be transfered, not editable later.
   * @param type - Used to specify item-type for searching, editable later.
   * @param name - Used to search for item by name, editable later.
   * @param description - Description of the item, editable later.
   * @param id - Used to present an item-identifyer in the ui(view), not editable later.
   * @param dayOfCreation - Metadata when item was created, not editable later.
   * @param costPerDay - Used in calculations to set up lending contracts, editable later.
   */
  public Item(
      // TODO: Findbugs issue should be supressed as the owner should reflect the member in the sls!
      Member owner,
      ItemType type,
      String name,
      String description,
      String id,
      int dayOfCreation,
      int costPerDay) {
    this.owner = owner;
    this.type = type;
    this.name = name;
    this.description = description;
    this.id = id;
    this.dayOfCreation = dayOfCreation;
    this.costPerDay = costPerDay;
    this.isReserved = false;
  }

  /**
   * Instanciate an item with the data presented as parameters including isReserved.
   *
   * @param owner - Used to identify the owner when/if credits are to be transfered, not editable later.
   * @param type - Used to specify item-type for searching, editable later.
   * @param name - Used to search for item by name, editable later.
   * @param description - Description of the item, editable later.
   * @param id - Used to present an item-identifyer in the ui(view), not editable later.
   * @param dayOfCreation - Metadata when item was created, not editable later.
   * @param costPerDay - Used in calculations to set up lending contracts, editable later.
   * @param isReserved - Signals if item is available for loan at the moment.
   */
  public Item(
      // TODO: Findbugs issue should be supressed as the owner should reflect the member in the sls.
      Member owner,
      ItemType type,
      String name,
      String description,
      String id,
      int dayOfCreation,
      int costPerDay,
      boolean isReserved) {
    this.owner = owner;
    this.type = type;
    this.name = name;
    this.description = description;
    this.id = id;
    this.dayOfCreation = dayOfCreation;
    this.costPerDay = costPerDay;
    this.isReserved = isReserved;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getIsReserved() {
    return isReserved;
  }

  public void setReserved(boolean isReserved) {
    this.isReserved = isReserved;
  }

  public String getId() {
    return id;
  }
  
  /**
   * Get a copy of the Member-object representing the owner of the item.
   *
   * @return - A copy of the owner.
   */
  public Member getOwner() {
    Member ownerCopy = new Member(
        this.owner.getFirstName(),
        this.owner.getLastName(),
        this.owner.getEmail(),
        this.owner.getPhoneNumber(),
        this.owner.getId(),
        this.owner.getRegistredDay(),
        this.owner.getCredits());

    return ownerCopy;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  public int getDayOfCreation() {
    return dayOfCreation;
  }

  public ItemType getType() {
    return type;
  }
}
