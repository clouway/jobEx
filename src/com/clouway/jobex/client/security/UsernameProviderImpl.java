package com.clouway.jobex.client.security;

import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UsernameProviderImpl implements UsernameProvider {

  private final String usernameCookie = "username";

  @Override
  public String getUsername() {
//    return Cookies.getCookie(usernameCookie);
    return "Petar";
  }

  @Override
  public void setUsername(String username) {
    Cookies.setCookie(usernameCookie, username);
  }
}
