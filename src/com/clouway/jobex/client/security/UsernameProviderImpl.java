package com.clouway.jobex.client.security;

import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UsernameProviderImpl implements UsernameProvider {


  @Override
  public String getUsername() {
    return Cookies.getCookie("username");
  }

  @Override
  public void setUsername(String username) {
    Cookies.setCookie("username", username);
  }
}
