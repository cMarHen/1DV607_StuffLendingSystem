package model.repository;

import java.util.ArrayList;

import model.repository.mock.IMock;

public class MapperFactory {
  ArrayList<IMock> mocks;

  public MapperFactory() {
  }

  // OBS! Considered as anti-patten, should return a map with ALL mappers
  // to be used in PersistenceFacade.
  public IMapper getMemberMapper() {
    return new MemberMapper();
  }

  /* public IMapper getItemMapper() {
    return new ItemMapper();
  } */
}
