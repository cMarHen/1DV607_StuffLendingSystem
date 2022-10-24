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
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public MemberCollection getMemberCollection() {
    ArrayList<MemberDto> members = mapperFactory.getMemberMapper().getAll();

    for (MemberDto dto : members) {
      Member.Mutable newMember = new Member.Mutable(
          dto.getFirstName(),
          dto.getLastName(),
          dto.getEmail(),
          dto.getPhoneNumber(),
          dto.getId(),
          dto.getRegistredDay(),
          dto.getCredits());
      memberCollection.addMember(newMember);
    }
    return memberCollection;
  }

  /**
   * Get ItemCollection, populated with items from storage.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public ItemCollection getItemCollection() {
    ArrayList<ItemDto> items = mapperFactory.getItemMapper().getAll();
    
    for (ItemDto dto : items) {
      Item.Mutable newItem = new Item.Mutable(
          findMemberById(dto.getOwnerId()),
          Item.ItemType.valueOf(dto.getType()),
          dto.getName(),
          dto.getDescription(),
          dto.getId(),
          dto.getDayOfCreation(),
          dto.getCostPerDay(),
          dto.getIsReserved());
  
      itemCollection.addItem(newItem);
    }
    return itemCollection;
  }

  /**
   * Get ContractCollection, populated with contracts from storage.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Want to keep the reference.")
  public ContractCollection getContractCollection() {
    ArrayList<ContractDto> contracts = mapperFactory.getContractMapper().getAll();

    for (ContractDto dto : contracts) {
      LendingContract newContract = new LendingContract(
          findMemberById(dto.getLenderId()),
          dto.getEndDay(),
          findItemById(dto.getItemId()),
          dto.getStartDay());


      newContract.setValidated(dto.getIsValidated());
      contractCollection.addContract(newContract);
    }

    return contractCollection;
  }

  /**
   * Save all current data stored in collection.
   */
  public void save() {
    saveAllMembers();
    saveAllItems();
    saveAllContracts();
  }

  private void saveAllMembers() {
    ArrayList<MemberDto> memberDtos = new ArrayList<>();
    Iterable<Member.Mutable> domainMembers = memberCollection.getMembers();

    // Save all members
    for (Member.Mutable m : domainMembers) {
      MemberDto dto = new MemberDto(
          m.getFirstName(),
          m.getLastName(),
          m.getEmail(),
          m.getPhoneNumber(),
          m.getId(),
          m.getCredits(),
          m.getRegistredDay());

      memberDtos.add(dto);
    }

    mapperFactory.getMemberMapper().saveAll(memberDtos);
  }

  private void saveAllItems() {
    ArrayList<ItemDto> itemDtos = new ArrayList<>();
    Iterable<Item.Mutable> domainItems = itemCollection.getAllItems();
    
    for (Item.Mutable i : domainItems) {
      ItemDto dto = new ItemDto(
          i.getOwner().getId(),
          i.getType().toString(),
          i.getName(),
          i.getDescription(),
          i.getId(),
          i.getDayOfCreation(),
          i.getCostPerDay(),
          i.getIsReserved());
        
      itemDtos.add(dto);
    }

    mapperFactory.getItemMapper().saveAll(itemDtos);
  }
    
  private void saveAllContracts() {
    ArrayList<ContractDto> contractDtos = new ArrayList<>();
    ArrayList<LendingContract> domainContracts = new ArrayList<>();
    Iterable<Item.Mutable> domainItems = itemCollection.getAllItems();

    // Get domain contracts
    for (Item.Mutable item : domainItems) {
      ArrayList<LendingContract> contracts = contractCollection.getContractsByItem(item);
      if (contracts.size() > 0) {
        for (LendingContract c : contracts) {
          domainContracts.add(c);
        }
      }
    }

    // Create contractDtos
    for (LendingContract c : domainContracts) {
      ContractDto newContract = new ContractDto(
          c.getStartDay(),
          c.getEndDay(),
          c.getTotalContractFee(),
          c.getItem().getId(),
          c.getLender().getId(),
          c.getIsValidated());


      contractDtos.add(newContract);
    }

    mapperFactory.getContractMapper().saveAll(contractDtos);
  }
  
  private Item.Mutable findItemById(String itemId) {
    Item.Mutable item = itemCollection.findItemById(itemId);
    return item;
  }

  private Member.Mutable findMemberById(String ownerId) {
    Member.Mutable m = memberCollection.findMemberById(ownerId);
    return m;
  }
}
