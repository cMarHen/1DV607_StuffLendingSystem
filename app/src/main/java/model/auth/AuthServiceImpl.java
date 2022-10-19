package model.auth;

import javax.naming.AuthenticationException;

/**
 * Class AuthImpl. Implements Auth.
 */
public class AuthServiceImpl implements AuthService {
  AuthServiceHandler handler;

  public AuthServiceImpl() {
    this.handler = new AuthServiceHandler();
  }

  @Override
  public String authenticate(String username, String password) throws AuthenticationException {
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

  @Override
  public String unRegister(String username, String password) throws Exception {
    try {
      return handler.unRegister(username);
    } catch (Exception e) {
      throw new Exception("Error: Un-register user failed");
    } 
  }

}
