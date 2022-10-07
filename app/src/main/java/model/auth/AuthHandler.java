package model.auth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.naming.AuthenticationException;

/**
 * Class AuthHandler. 
 */
public class AuthHandler {
  private String projectPath;
  private String relativePathToProject;
  private ArrayList<User> users;

  protected AuthHandler() {
    this.projectPath = "/src/main/java/model/auth/mock-files/registry.data";
    this.relativePathToProject = new File("").getAbsolutePath();
    this.users = new ArrayList<>();
    this.loadFiles();
  }

  protected String register(String username, String password) throws Exception {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        throw new Exception();
      }
    }

    User newUser = new User(username, password);

    if (newUser != null) {
      users.add(newUser);
    }
    
    this.saveFiles();
    return username;
  }

  protected String login(String username, String password) throws AuthenticationException {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return username;
      }
    }

    throw new AuthenticationException();
  }

  private void saveFiles() {
    try {
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      String str = "";
      for (User user : users) {
        str += user.getUsername() + ":" + user.getPassword() + "\n";
      }

      bw.write(str);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadFiles() {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8)); 

      // Read from file, and create a new User.
      String line = null;
      while ((line = br.readLine()) != null) {
        users.add(createUserFromFile(line));
      }      

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private User createUserFromFile(String line) {
    String[] arr = line.split(":");
    return new User(arr[0], arr[1]);
  }

  
}
