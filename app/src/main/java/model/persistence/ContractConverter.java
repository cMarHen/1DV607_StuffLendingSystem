package model.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class ContractConverter.
 */
public class ContractConverter implements StorageConverter<ContractDto> {
  private String projectPath;
  private String relativePathToProject;

  public ContractConverter() {
    this.projectPath = "/src/main/java/model/persistence/mock-files/members.data";
    this.relativePathToProject = new File("").getAbsolutePath();
  }

  @Override
  public ContractDto get(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ContractDto> getAll() {
    ArrayList<ContractDto> contracts = loadContractsFromDb();
    return contracts;
  }

  
  @Override
  public void put(ArrayList<ContractDto> contracts) {
    writeContractsToDb(contracts);    
  }


  
  private void writeContractsToDb(ArrayList<ContractDto> contracts) {
    try {
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      String str = "";
      for (ContractDto c : contracts) {
        str 
          += c.getStartDay() + ":"
          + c.getEndDay() + ":"
          + c.getTotalContractFee() + ":"
          + c.getItemId() + ":"
          + c.getLenderId()
          + "\n";
      }

      bw.write(str);
      bw.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<ContractDto> loadContractsFromDb() {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      ArrayList<ContractDto> contracts = new ArrayList<>();
      String line = null;
      while ((line = br.readLine()) != null) {
        contracts.add(createContractFromFile(line));
      }

      br.close();
      return contracts; 
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private ContractDto createContractFromFile(String line) {
    String[] arr = line.split(":");
    ContractDto c = new ContractDto(
        Integer.parseInt(arr[0]),
        Integer.parseInt(arr[1]),
        Integer.parseInt(arr[2]),
        arr[3],
        arr[4]
      );

    return c;
  }
}
