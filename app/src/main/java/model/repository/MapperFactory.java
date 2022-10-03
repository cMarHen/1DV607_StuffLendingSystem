package model.repository;

/**
 * Factory for Persistence mappers. 
 */
public class MapperFactory {
  public MapperFactory() {
  }

  /* public Mapper getMapper(MapperType type) {
    if (type.equals(MapperType.MEMBER_MAPPER)) {
      return this.memberMapper;
    }
  } */

  // OBS! Considered as anti-patten, should return a map with ALL mappers
  // to be used in PersistenceFacade.
  /* public Mapper getMemberMapper() {
    return new MemberMapper();
  } */

  /* public IMapper getItemMapper() {
    return new ItemMapper();
  } */
}
