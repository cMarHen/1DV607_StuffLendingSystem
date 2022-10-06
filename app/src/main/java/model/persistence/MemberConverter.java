package model.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import model.domain.Member;

/**
 * Handles DB relation for Member.
 */
public class MemberConverter implements StorageConverter<Member.Mutable> {
  private String projectPath;
  private String relativePathToProject;

  public MemberConverter() {
    this.projectPath = "/src/main/java/model/persistence/mock-files/members.data";
    this.relativePathToProject = new File("").getAbsolutePath();
  }

  @Override
  public Member.Mutable get(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Member.Mutable> getAll() {
    ArrayList<Member.Mutable> members = loadMembersFromDb();
    return members;
  }

  @Override
  public void put(ArrayList<Member.Mutable> members) {
    writeMembersToDb(members);
  }

  private void writeMembersToDb(ArrayList<Member.Mutable> members) {
    try {
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      String str = "";
      for (Member.Mutable m : members) {
        str 
          += m.getId() + ":"
          + m.getFirstName() + ":"
          + m.getLastName() + ":"
          + m.getEmail() + ":"
          + m.getPhoneNumber() + ":"
          + m.getRegistredDay() + ":"
          + m.getCredits()
          + "\n";
      }

      bw.write(str);
      bw.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<Member.Mutable> loadMembersFromDb() {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      ArrayList<Member.Mutable> members = new ArrayList<>();
      String line = null;
      while ((line = br.readLine()) != null) {
        members.add(createMemberFromFile(line));
      }

      br.close();
      return members; 
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Member.Mutable createMemberFromFile(String line) {
    String[] arr = line.split(":");
    Member.Mutable m = new Member.Mutable(
        arr[1],
        arr[2],
        arr[3],
        arr[4],
        arr[0],
        Integer.parseInt(arr[5]),
        Integer.parseInt(arr[6])
    );

    return m;
  }
  
}
