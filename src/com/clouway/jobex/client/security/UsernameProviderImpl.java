package com.clouway.jobex.client.security;

import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UsernameProviderImpl implements UsernameProvider {

  private final String usernameCookie = "mail@mail.com";

  @Override
  public String getUsername() {
    return usernameCookie;
  }

  @Override
  public void setUsername(String username) {
    Cookies.setCookie(usernameCookie, username);
  }
}
