package model.persistence;

import java.util.ArrayList;

/**
 * Class ContractMapper, used for mapping contracts from DB.
 */
public class ContractMapper implements Mapper<ContractDto> {
  private ContractConverter converter;

  public ContractMapper() {
    this.converter = new ContractConverter();
  }

  @Override
  public ContractDto getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ContractDto> getAll() {
    return converter.getAll();
  }

  @Override
  public void saveAll(ArrayList<ContractDto> resources) {
    converter.put(resources);
  }

  @Override
  public Boolean delete(String id) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
