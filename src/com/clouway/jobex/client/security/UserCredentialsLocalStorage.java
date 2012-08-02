package com.clouway.jobex.client.security;

/**
 * Stores User credentials
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserCredentialsLocalStorage {

  String getSID();

  String getUsername();

  void setUserCredentials(String email, String sid);

  boolean isAuthorized();

  void deleteCookies();
}
