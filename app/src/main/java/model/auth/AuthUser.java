package model.auth;

/**
 * Class User.
 */
public class AuthUser {
  private String username;
  private String password;

  protected AuthUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public String getId() {
    return username;
  }
}
