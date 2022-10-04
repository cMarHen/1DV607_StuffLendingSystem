package model.repository.mock;

/**
 * Mock for contract nr 1.
 */
public class ContractMockOne implements Mock {
  private String oid = "oid_264345";
  private String startDay = "5";
  private String endDay = "7";
  private String totalContractFee = "0";
  private String itemId = "item_5yR0oc";
  private String lenderId = "2yGoOc";

  public ContractMockOne() {
  }

  @Override
  public String getColumn(String column) {
    if (column.equals("OID")) {
      return this.oid;
    } else if (column.equals("LENDER_ID")) {
      return this.lenderId;
    } else if (column.equals("START_DAY")) {
      return this.startDay;
    } else if (column.equals("END_DAY")) {
      return this.endDay;
    } else if (column.equals("TOTAL_COST")) {
      return this.totalContractFee;
    } else if (column.equals("ITEM_ID")) {
      return this.itemId;
    }

    return null;
  }
  
}
