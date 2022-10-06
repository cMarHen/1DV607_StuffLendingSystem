package model.persistence;

import java.util.ArrayList;

/**
 * Class MemberMapper, used for mapping members from DB.
 */
public class MemberMapper implements Mapper<MemberDto> {
  private MemberConverter converter;

  public MemberMapper() {
    this.converter = new MemberConverter();
  }

  @Override
  public MemberDto getById(String id) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<MemberDto> getAll() {
    return converter.getAll();
  }

  @Override
  public Boolean delete(String id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void saveAll(ArrayList<MemberDto> resources) {
    converter.put(resources);
  }
  
}
