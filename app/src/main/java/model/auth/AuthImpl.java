package model.auth;

import javax.naming.AuthenticationException;

/**
 * Class AuthImpl. Implements Auth.
 */
public class AuthImpl implements Auth {
  AuthHandler handler;

  public AuthImpl() {
    this.handler = new AuthHandler();
  }

  @Override
  public String login(String username, String password) throws AuthenticationException {
    try {
      return handler.login(username, password);
    } catch (Exception e) {
      throw new AuthenticationException();
    }
  }

  @Override
  public String register(String username, String password) throws Exception {
    try {
      return handler.register(username, password);
    } catch (Exception e) {
      throw new Exception("Error: Register user failed");
    }    
  }

}
