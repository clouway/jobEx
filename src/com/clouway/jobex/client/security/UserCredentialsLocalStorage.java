package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserCredentialsLocalStorage {


  public static final String USER = "user";

  public static final String COMPANY = "company";

  String getSID();

  String getUsername();

  void setUserCredentials(String email, String sid);

  boolean isAuthorized();

  void setCompanyCredentials(String email, String sid);
}
