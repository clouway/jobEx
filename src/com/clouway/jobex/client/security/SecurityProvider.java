package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityProvider {
  boolean isAuthorized();

  void setCredentials(String id, String email, String loginType);

}
