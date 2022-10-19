package model.auth;

import javax.naming.AuthenticationException;

/**
 * Interface for Auth.
 */
public interface AuthService {
  public String authenticate(String username, String password) throws AuthenticationException;

  public String register(String username, String password) throws Exception;

  public String unRegister(String username, String password) throws Exception;
}
