package model.repository;

import java.util.ArrayList;

import model.repository.mock.Mock;

public class MapperFactory {
  ArrayList<Mock> mocks;

  public MapperFactory() {
  }

  // OBS! Considered as anti-patten, should return a map with ALL mappers
  // to be used in PersistenceFacade.
  public Mapper getMemberMapper() {
    return new MemberMapper();
  }

  /* public IMapper getItemMapper() {
    return new ItemMapper();
  } */
}
