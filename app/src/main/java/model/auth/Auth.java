package model.auth;

import javax.naming.AuthenticationException;

/**
 * Interface for Auth.
 */
public interface Auth {
  public String login(String username, String password) throws AuthenticationException;

  public String register(String username, String password) throws Exception;
}
