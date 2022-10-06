package model.persistence;

/**
 * Class ItemDto.
 */
public class ItemDto {
  private String ownerId;
  private String type;
  private String name;
  private String description;
  private String id;
  private int dayOfCreation;
  private int costPerDay;
  private boolean isReserved;

  /**
   * Constructor for ItemDto.
   */
  public ItemDto(
      String ownerId,
      String type,
      String name,
      String description,
      String id,
      int dayOfCreation,
      int costPerDay,
      boolean isReserved
  ) {
    this.ownerId = id;
    this.type = type;
    this.name = name;
    this.description = description;
    this.id = id;
    this.dayOfCreation = dayOfCreation;
    this.costPerDay = costPerDay;
    this.isReserved = isReserved;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public int getDayOfCreation() {
    return dayOfCreation;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getType() {
    return type;
  }

  public Boolean getIsReserved() {
    return isReserved;
  }

}
