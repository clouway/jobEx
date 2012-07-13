package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UsernameProvider {

  String getUsername();

  void setUsername(String username);

}
