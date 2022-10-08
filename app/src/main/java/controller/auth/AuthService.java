package controller.auth;

import controller.Auth;
import javax.naming.AuthenticationException;

/**
 * Interface for Auth.
 */
public interface AuthService {
  public String authenticate(Auth auth) throws AuthenticationException;

  public String register(Auth auth) throws Exception;

  public String unRegister(Auth auth) throws Exception;
}
