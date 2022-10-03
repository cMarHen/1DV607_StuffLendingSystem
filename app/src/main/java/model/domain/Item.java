package model.domain;

/**
 * Class representing an item in the stufflending system.
 *
 */
public class Item {
  private Member.Mutable owner;
  protected boolean isReserved;
  private ItemType type;
  protected String name;
  protected String description;
  private String id;
  private int dayOfCreation;
  protected int costPerDay;

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
   * Instanciate an item with Item-object.
   *
   * @param item - Readonly Item-object.
   */
  public Item(Item item) {
    this.type = item.getType();
    this.name = item.getName();
    this.description = item.getDescription();
    this.costPerDay = item.getCostPerDay();
    this.isReserved = item.getIsReserved();
    this.owner = item.getOwner();
    this.dayOfCreation = item.getDayOfCreation();
    this.id = item.getId();
  }

  /**
   * Instanciate an item with the data presented as parameters, sets isReserved to false.
   *
   * @param type - Used to specify item-type for searching, editable later.
   * @param name - Used to search for item by name, editable later.
   * @param description - Description of the item, editable later.
   * @param costPerDay - Used in calculations to set up lending contracts, editable later.
   */
  public Item(
      ItemType type,
      String name,
      String description,
      int costPerDay) {
    this.type = type;
    this.name = name;
    this.description = description;
    this.costPerDay = costPerDay;
    this.isReserved = false;
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
      Member.Mutable owner,
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
      Member.Mutable owner,
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

  public String getDescription() {
    return description;
  }

  public boolean getIsReserved() {
    return isReserved;
  }

  public String getId() {
    return id;
  }

  public Member.Mutable getOwner() {
    return this.owner;
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

  /**
   * Mutable Item class.
   *
   */
  public static class Mutable extends Item {
    public Mutable(Item item) {
      super(item);
    }

    public Mutable(
        ItemType type,
        String name,
        String description,
        int costPerDay
    ) {
      super(type, name, description, costPerDay);
    }

    public Mutable(
        Member.Mutable owner,
        ItemType type,
        String name,
        String description,
        String id,
        int dayOfCreation,
        int costPerDay
    ) {
      super(owner, type, name, description, id, dayOfCreation, costPerDay);
    }

    public Mutable(
        Member.Mutable owner,
        ItemType type,
        String name,
        String description,
        String id,
        int dayOfCreation,
        int costPerDay,
        boolean isReserved
    ) {
      super(owner, type, name, description, id, dayOfCreation, costPerDay, isReserved);
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public void setReserved(boolean isReserved) {
      this.isReserved = isReserved;
    }

    public void setCostPerDay(int costPerDay) {
      this.costPerDay = costPerDay;
    }
  }
}
