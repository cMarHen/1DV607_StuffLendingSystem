package controller;

/**
 * Class as encapsulated Password.
 */
public class Password {
  private String password;

  public Password(String passwordString) {
    // TODO: Validate password.
    this.password = passwordString;
  }

  public String getPassword() {
    return password;
  }
}
