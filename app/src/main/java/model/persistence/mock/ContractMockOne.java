package model.persistence.mock;

/**
 * Mock for contract nr 1.
 */
public class ContractMockOne implements Mock {
  private String oid = "oid_264345";
  private String startDay = "5";
  private String endDay = "7";
  private String totalContractFee = "30";
  private String itemId = "item_2yG1Oc";
  private String lenderId = "HyG1P3";

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
