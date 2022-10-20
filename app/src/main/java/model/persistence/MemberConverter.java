package model.persistence;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Handles DB relation for Member.
 */
public class MemberConverter implements StorageConverter<MemberDto> {
  private String projectPath;
  private String relativePathToProject;

  public MemberConverter() {
    this.projectPath = "/src/main/java/model/persistence/mock-files/members.json";
    this.relativePathToProject = new File("").getAbsolutePath();
  }

  @Override
  public MemberDto get(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<MemberDto> getAll() {
    ArrayList<MemberDto> members = loadMembersFromDb();
    return members;
  }

  @Override
  public void put(ArrayList<MemberDto> members) {
    writeMembersToDb(members);
  }

  private void writeMembersToDb(ArrayList<MemberDto> members) {
    try {
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      // Implementation borrowed from https://attacomsian.com/blog/gson-read-json-file
      Gson gson = new Gson();
      gson.toJson(members, bw);

      bw.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<MemberDto> loadMembersFromDb() {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      
      // Implementation borrowed from https://attacomsian.com/blog/gson-read-json-file
      Gson gson = new Gson();
      ArrayList<MemberDto> members = gson.fromJson(br, new TypeToken<ArrayList<MemberDto>>() {}.getType());

      br.close();
      return members; 
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }  
}
