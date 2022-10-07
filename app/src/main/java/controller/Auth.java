package controller;

public class Auth {
  private String id;
  private String password;

  public Auth(String id, Password password) {
    this.id = id;
    this.password = password.getPassword();
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }
}
