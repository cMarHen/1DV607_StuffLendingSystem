package model.repository;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Factory for Persistence mappers. 
 */
public class MapperFactory {
  private MemberMapper memberMapper;
  private ItemMapper itemMapper;

  public MapperFactory() {
    this.memberMapper = new MemberMapper();
    this.itemMapper = new ItemMapper(this.memberMapper.loadAll());
  }

  /* public Mapper getMapper(MapperType type) {
    if (type.equals(MapperType.MEMBER_MAPPER)) {
      return this.memberMapper;
    }
  } */

  // OBS! Considered as anti-patten, should return a map with ALL mappers
  // to be used in PersistenceFacade.
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public MemberMapper getMemberMapper() {
    return memberMapper;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ItemMapper getItemMapper() {
    return itemMapper;
  }
}
