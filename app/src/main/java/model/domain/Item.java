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
   * Instanciate an item with the data presented as parameters.
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
    // TODO: Findbugs issue should be supressed as the owner should reflect the member in the sls.
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
  
  public Member getOwner() {
    // TODO: Findbugs issue should be supressed as the owner should be available to recieve credits.
    return owner;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public int getDayOfCreation() {
    return dayOfCreation;
  }

  public ItemType getType() {
    return type;
  }
}
