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
 * Class ItemConverter.
 */
public class ItemConverter implements StorageConverter<ItemDto> {
  private String projectPath;
  private String relativePathToProject;

  public ItemConverter() {
    this.projectPath = "/src/main/java/model/persistence/mock-files/items.data";
    this.relativePathToProject = new File("").getAbsolutePath();
  }

  @Override
  public ItemDto get(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ItemDto> getAll() {
    ArrayList<ItemDto> items = loadItemsFromDb();
    return items;
  }

  
  @Override
  public void put(ArrayList<ItemDto> resources) {
    writeItemsToDb(resources);
  }

  private void writeItemsToDb(ArrayList<ItemDto> items) {
    try {
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      String str = "";
      for (ItemDto i : items) {
        str 
          += i.getOwnerId() + ":"
          + i.getType() + ":"
          + i.getName() + ":"
          + i.getDescription() + ":"
          + i.getId() + ":"
          + i.getDayOfCreation() + ":"
          + i.getCostPerDay() + ":"
          + i.getIsReserved()
          + "\n";
      }

      bw.write(str);
      bw.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private ArrayList<ItemDto> loadItemsFromDb() {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      ArrayList<ItemDto> items = new ArrayList<>();
      String line = null;
      while ((line = br.readLine()) != null) {
        items.add(createItemFromFile(line));
      }

      br.close();
      return items; 
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private ItemDto createItemFromFile(String line) {
    String[] arr = line.split(":");
    ItemDto i = new ItemDto(
        arr[0],
        arr[1],
        arr[2],
        arr[3],
        arr[4],
        Integer.parseInt(arr[5]),
        Integer.parseInt(arr[6]),
        arr[7].equals("true")
    );
    return i;
  }
}
