package controller.auth;

import controller.Auth;
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
  public String login(Auth auth) throws AuthenticationException {
    try {
      return handler.login(auth.getId(), auth.getPassword());
    } catch (Exception e) {
      throw new AuthenticationException();
    }
  }

  @Override
  public String register(Auth auth) throws Exception {
    try {
      return handler.register(auth.getId(), auth.getPassword());
    } catch (Exception e) {
      throw new Exception("Error: Register user failed");
    }    
  }

}
