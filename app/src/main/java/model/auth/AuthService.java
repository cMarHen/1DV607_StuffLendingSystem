package model.auth;

import controller.Auth;
import javax.naming.AuthenticationException;

/**
 * Interface for Auth.
 */
public interface AuthService {
  public String login(Auth auth) throws AuthenticationException;

  public String register(Auth auth) throws Exception;
}
