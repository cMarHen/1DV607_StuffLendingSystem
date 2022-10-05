package model.repository.mock;

/**
 * Mock for Item nr 2. 
 */
public class ItemMockTwo implements Mock {
  String oid = "oid_233456";
  String ownerId = "2yGoOc";
  String type = "Game";
  String name = "Minecraft";
  String description = "Build your world";
  String id = "item_2yG1Oc";
  String dayOfCreation = "0";
  String costPerDay = "10";
  String isReserved = "false";

  public ItemMockTwo() {
  }

  @Override
  public String getColumn(String column) {
    if (column.equals("OID")) {
      return this.oid;
    } else if (column.equals("OWNER_ID")) {
      return this.ownerId;
    } else if (column.equals("TYPE")) {
      return this.type;
    } else if (column.equals("NAME")) {
      return this.name;
    } else if (column.equals("DESCRIPTION")) {
      return this.description;
    } else if (column.equals("ALPHA_ID")) {
      return this.id;
    } else if (column.equals("CREATION_DAY")) {
      return this.dayOfCreation;
    } else if (column.equals("COST")) {
      return this.costPerDay;
    } else if (column.equals("IS_RESERVED")) {
      return this.isReserved;
    } 
    
    return null;
  }
}
