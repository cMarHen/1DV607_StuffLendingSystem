package model.repository.mock;

public class ItemMockOne implements IMock {
  String oid = "oid_232345";
  String ownerID = "ownerID"; // TODO: This is owner ID, not Member
  String type = "Tool";
  String name = "Kratta";
  String description = "Krattar gräsmattan";
  String id = "item_01234";
  String dayOfCreation = "0";
  String costPerDay = "20";
  String isReserved = "false";

  public ItemMockOne() {
  }

  @Override
  public String getColumn(String column) {
    if (column.equals("OID")) {
      return this.oid;
    } else if(column.equals("TYPE")) {
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
