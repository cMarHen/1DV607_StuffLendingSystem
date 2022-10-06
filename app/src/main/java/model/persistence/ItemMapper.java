package model.persistence;

import java.util.ArrayList;

/**
 * Class ItemMapper, used for mapping items from DB.
 * RDBMapper : Item
 */
public class ItemMapper implements Mapper<ItemDto> {
  private ItemConverter converter;

  public ItemMapper() {
    this.converter = new ItemConverter();
  }

  @Override
  public ItemDto getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ItemDto> getAll() {
    return converter.getAll();
  }

  @Override
  public void saveAll(ArrayList<ItemDto> resources) {
    converter.put(resources);
  }

  @Override
  public Boolean delete(String id) {
    // TODO Auto-generated method stub
    return false;
  }
}
