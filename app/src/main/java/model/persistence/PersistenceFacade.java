package model.persistence;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.ContractCollection;
import model.domain.ContractCollectionImpl;
import model.domain.Item;
import model.domain.ItemCollection;
import model.domain.ItemCollectionImpl;
import model.domain.LendingContract;
import model.domain.Member;
import model.domain.MemberCollection;

/**
 * Facade for Persistence handling.
 */
public class PersistenceFacade {
  private MapperFactory mapperFactory;
  private MemberCollection memberCollection;
  private ItemCollection itemCollection;
  private ContractCollection contractCollection;

  /**
   * Constructor for PersistenceFacade.
   * Load members and items on init.
   */
  public PersistenceFacade() {
    this.mapperFactory = new MapperFactory();
    this.memberCollection = new MemberCollection();
    this.itemCollection = new ItemCollectionImpl();
    this.contractCollection = new ContractCollectionImpl();
  }

  /**
   * Get MemberCollection, populated with members from storage.
   */  
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public MemberCollection getMemberCollection() {
    ArrayList<MemberDto> members = mapperFactory.getMemberMapper().getAll();

    // TRANSFORM FROM MEMBERDTO to Member.Mutable

    for (Member.Mutable m : members) {
      memberCollection.addMember(m);
    }
    return memberCollection;
  }

  /**
   * Get ItemCollection, populated with items from storage.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ItemCollection getItemCollection() {
    ArrayList<Item.Mutable> items = mapperFactory.getItemMapper().loadAll();
    
    for (Item.Mutable i : items) {
      itemCollection.addItem(i);
    }
    return itemCollection;
  }

  /**
   * Get ContractCollection, populated with contracts from storage.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference")
  public ContractCollection getContractCollection() {
    ArrayList<LendingContract> contracts = mapperFactory.getContractMapper().loadAll();
    
    for (LendingContract c : contracts) {
      contractCollection.addContract(c);
    }
    return contractCollection;
  }
}
